import json
import random
import re
import sqlite3
import scipy as sp
import spotipy
from spotipy.oauth2 import SpotifyClientCredentials
from collections import Counter
import torch
from dialoguekit.platforms import FlaskSocketPlatform
from dialoguekit.core.annotated_utterance import AnnotatedUtterance
from dialoguekit.core.dialogue_act import DialogueAct
from dialoguekit.core.utterance import Utterance
from dialoguekit.participant.agent import Agent
from dialoguekit.participant.participant import DialogueParticipant
from transformers import AutoModelForSequenceClassification, AutoTokenizer,T5ForConditionalGeneration,T5TokenizerFast

class MusicRecommenderAgent(Agent):
    def __init__(self, id: str):
        """Music Recommender Agent.

        This agent manages multiple music playlists by processing user commands.
        The user can create, add to, remove from, view, or clear playlists.
        
        Args:
            id: Agent id.
        """
        super().__init__(id)
        self.num_user_msg = 0

        # Cargar el modelo y el tokenizador desde el directorio guardado
        bert_model_path = "models/finetuned_bert-base-uncased"
        self.bert_model = AutoModelForSequenceClassification.from_pretrained(bert_model_path)
        self.bert_tokenizer = AutoTokenizer.from_pretrained(bert_model_path)
        
        roberta_model_path = "models/finetuned_roberta-base"
        self.roberta_model = AutoModelForSequenceClassification.from_pretrained(roberta_model_path)
        self.roberta_tokenizer = AutoTokenizer.from_pretrained(roberta_model_path)
        
        t5_model_path = "models/finetuned_t5-small"
        self.t5_model = T5ForConditionalGeneration.from_pretrained(t5_model_path)
        self.t5_tokenizer = T5TokenizerFast.from_pretrained(t5_model_path)

        
        # Configura tus credenciales (sustituye por las tuyas)
        self.sp = spotipy.Spotify(auth_manager=SpotifyClientCredentials(
            client_id="4814f01af6c749d19583b43bedebbf0f",
            client_secret="8ef9f6e98adc4c1ead2de06326597da6"
        ))
        
        # Connect to the database
        self.connection = sqlite3.connect('data/music_database.db', check_same_thread=False)
        self.cursor = self.connection.cursor()

        self.waiting_for_song_selection = False
        self.last_added_song_id = 0
        self.add_flag = False
        self.possible_songs = []

    def welcome(self) -> None:
        """Sends the agent's welcome message."""
        utterance = AnnotatedUtterance(
            "Hello, I'm your music assistant. You can manage your playlist by adding, removing, viewing, or clearing it.",
            participant=DialogueParticipant.AGENT,
        )
        self._dialogue_connector.register_agent_utterance(utterance)
        self.display_help()

    def goodbye(self) -> None:
        """Sends the agent's goodbye message."""
        # Close the connection
        self.connection.close()
        utterance = AnnotatedUtterance(
            "It was nice managing your playlist. Bye!",
            # dialogue_acts=[DialogueAct(intent=self.stop_intent)],
            participant=DialogueParticipant.AGENT,
        )
        self._dialogue_connector.register_agent_utterance(utterance)

    def display_help(self) -> None:
        """Displays help information to the user."""
        help_message = (
            "You can interact with me using natural language. Just keep in mind that :\n\n"
            "- To add a song, mention both the title and the artist. If you only mention the title, I'll show you a list of matching songs to choose from.\n\n"

            "- To remove a song, you have to mention the title and the artist."
        )

        utterance = AnnotatedUtterance(
            help_message,
            participant=DialogueParticipant.AGENT,
        )
        self._dialogue_connector.register_agent_utterance(utterance)

    def receive_utterance(self, utterance: Utterance) -> None:
        """Gets called each time there is a new user utterance.

        This function processes playlist commands from the user.

        Args:
            utterance: User utterance.
        """
        message = utterance.text.lower()
        
        if message == "help":
            self.display_help()
            return
        
        response = ""        
        # Check if the agent is waiting for a song selection
        if self.waiting_for_song_selection:
            if message.isdigit():
                selected_index = int(message) - 1
                if selected_index == 0:  # El usuario selecciona "0" para no añadir ninguna canción
                    response = "No song was selected. Cancelling the operation."
                elif 0 <= selected_index < len(self.possible_songs):
                    selected_song = self.possible_songs[selected_index]
            
                    # Añadir la canción seleccionada a la lista de reproducción
                    response = self.add_to_playlist(
                                selected_song['title'],
                                selected_song['artist'],
                                selected_song['spotify_id'],
                                selected_song['album'],
                                selected_song['release_date'],
                                selected_song['genre'],
                            )
                    
                    # Clear the waiting flag
                    self.waiting_for_song_selection = False
                    self.possible_songs = []
                    
                else:
                    response = "Invalid selection. Please enter a valid number from the list."
            else:
                response = "Invalid input. Please enter a valid number."   
        
        else:
            # Detectar intención usando el modelo
            intent = self.predict_intent(message) 
            if intent == "add":
                title, artist = self.extract_song_info(message, intent)
                if(artist):
                    artist = self.normalize_input(artist)
                    
                print(title, artist)
                song_info = self.search_song_in_db(title, artist)

                if song_info:
                    if isinstance(song_info, list) and len(song_info) > 1:
                        # Si song_info tiene 10 canciones, devolver una lista con las canciones
                        response = "Found the following songs:\n"
                        for idx, song in enumerate(song_info, 1):
                            response += f"{idx}. {song['title']} by {song['artist']} (Album: {song['album']})\n"
                            
                        # Agregar una instrucción para que el usuario seleccione una canción
                        response += "\nPlease select the song number you want to add to your playlist (or enter 0 if you don't want to add any song)"
                        self.waiting_for_song_selection = True
                        # Rellenar self.possible_songs con las canciones encontradas
                        for idx, song in enumerate(song_info, 1):
                            self.possible_songs.append(song)  # Añadir la canción a la lista
                    else:
                        # Si song_info tiene solo una canción, añadirla a la lista de reproducción
                        response = self.add_to_playlist(
                            song_info['title'],
                            song_info['artist'],
                            song_info['spotify_id'],
                            song_info['album'],
                            song_info['release_date'],
                            song_info['genre'],
                        )
                else:
                    response = 'No song was found with that title.'

            
            elif intent == "remove":
                title, artist = self.extract_song_info(message, intent)
                
                # Normalize user input
                title = self.normalize_input(title)
                artist = self.normalize_input(artist)

                song_info = self.search_song_in_db(title,artist)
                
                response = self.remove_song_from_playlist(song_info['title'], song_info['artist'])
            
            elif intent == "view":
                response = self.view_playlist()
            
            elif intent == "clear":
                response = self.clear_playlist()

        agent_response = AnnotatedUtterance(
            response,
            participant=DialogueParticipant.AGENT,
        )
        self._dialogue_connector.register_agent_utterance(agent_response)
        
        if self.add_flag:
            recommended_songs = self.recommend_songs(self.last_added_song_id)
            if isinstance(recommended_songs, list) and len(recommended_songs) > 1:
                track = self.sp.track(self.last_added_song_id)
                song_name = track['name']
                
                response = f"As the last song you added was {song_name}, I recommend you these songs: \n"
                for idx, song in enumerate(recommended_songs, 1):
                    response += f"{idx}. {song['title']} by {song['artist']} (Album: {song['album']})\n"
                    
                self.add_flag = False
                agent_response = AnnotatedUtterance(
                    response,
                    participant=DialogueParticipant.AGENT,
                )
                self._dialogue_connector.register_agent_utterance(agent_response)

            
# ----------------------------------------------------------------------------------------------------------------------           
                
    def search_song_in_playlist(self, title: str, artist: str) -> str:
        """Search for a song in the playlist by title and artist.

        Args:
        title (str): The title of the song.
        artist (str): The name of the artist.

        Returns:
        str: A message indicating whether the song was found and its information.
        """

        query = '''SELECT * FROM Playlist_songs WHERE LOWER(REPLACE(song_name, \"'\", '')) = ? AND LOWER(artist_name) = ?;'''
        self.cursor.execute(query, (title, artist))
        result = self.cursor.fetchone()  # Obtiene la primera fila que coincide

        return result
       
    def search_song_in_db(self, title: str, artist: str = None):
        """Search for the most popular song in the database by title and artist.
        
        Args:
        title (str): The title of the song.
        artist (str): The name of the artist (optional).

        Returns:
        dict: The most popular song's details
        """
        
        # Construir el query de búsqueda
        query = title
        if artist:
            query += f" {artist}"
        
        print('query:', query)
        # Realizar la búsqueda en Spotify
        results = self.sp.search(q=query, limit=10, type='track')

        # Extraer los resultados de la búsqueda
        tracks = results['tracks']['items']
        
        if not tracks:
            return None  # Si no se encuentran canciones, devolver None

        if artist:
            # Si también se proporciona el artista, devolver solo la canción más popular
            most_popular_track = max(tracks, key=lambda track: track['popularity'])

            song_info = {
                'id': None,  # Este campo es para autoincremento (lo maneja la base de datos)
                'spotify_id': most_popular_track['id'],
                'title': most_popular_track['name'],
                'artist': ', '.join(artist['name'] for artist in most_popular_track['artists']),
                'album': most_popular_track['album']['name'],
                'genre': most_popular_track['album'].get('genres', 'unknown'),  # Puede no estar disponible
                'release_date': most_popular_track['album']['release_date'],
            }
            
            return song_info

        else:
            # Si solo se proporciona el título, devolver las 10 canciones más populares
            songs_info = []
            for track in tracks:
                song_info = {
                    'spotify_id': track['id'],
                    'title': track['name'],
                    'artist': ', '.join(artist['name'] for artist in track['artists']),
                    'album': track['album']['name'],
                    'genre': track['album'].get('genres', 'unknown'),
                    'release_date': track['album']['release_date'],
                    'popularity': track['popularity'],
                }
                songs_info.append(song_info)

            # Ordenar las canciones por popularidad en orden descendente
            songs_info.sort(key=lambda song: song['popularity'], reverse=True)
            return songs_info
        

    
#-------------------------------------ADD - REMOVE - VIEW - CLEAR--------------------------------------------------

    def add_to_playlist(self, title: str, artist: str, spotify_id: str, album: str, release_date: str, genre: str):
        """
        Adds a song to the user_songs table in the database.

        Args:
        title (str): The title of the song.
        artist (str): The name of the artist.
        spotify_id (str): The Spotify ID of the song.
        album (str): The album the song belongs to.
        release_date (str): The release date of the song.
        genre (str): The genre of the song.

        Returns:
        str: A confirmation message.
        """
        try:
            # Comprobar si la canción ya está en la base de datos
            self.cursor.execute("SELECT spotify_id FROM user_songs WHERE spotify_id = ?", (spotify_id,))
            existing_song = self.cursor.fetchone()

            if existing_song:
                return f"The song '{title}' by '{artist}' is already in your song library."
            
            # Añadir la canción a la tabla user_songs
            self.cursor.execute('''
                INSERT INTO user_songs (spotify_id, title, artist, album, genre, release_date)
                VALUES (?, ?, ?, ?, ?, ?)
            ''', (spotify_id, title, artist, album, genre, release_date))

            # Guardar los cambios en la base de datos
            self.connection.commit()

            response = f'Song "{title.title()}" by "{artist.title()}" added to your song library.'
            
            self.add_flag = True
            self.last_added_song_id = spotify_id

            return response
        
        except sqlite3.Error as e:
            return f"Error al añadir la canción a la base de datos: {e}"
        
    def view_playlist(self):
        """Returns the contents of the user_songs table."""    
        try:
            
            # Consultar todas las canciones en la tabla user_songs
            self.cursor.execute("SELECT * FROM user_songs")
            
            # Recuperar todos los resultados
            songs = self.cursor.fetchall()
            
            # Comprobar si hay canciones
            if not songs:
                return "The playlist is empty."

            # Mostrar los resultados
            return "\n".join([f"{song[2]} by {song[3]} (Album: {song[4]})" for song in songs])

      
        except sqlite3.Error as e:
            print(f"Error al acceder a la base de datos: {e}")

    def remove_song_from_playlist(self, title: str, artist: str = None):
        """
        Removes a song from the user_songs table in the database by title and optionally by artist.

        Args:
        title (str): The title of the song to remove.
        artist (str): The name of the artist (optional).

        Returns:
        str: A confirmation message or an error message.
        """
        try:
            # Construir la consulta SQL dependiendo de si se pasa el artista
            if artist:
                self.cursor.execute('''
                    DELETE FROM user_songs WHERE title = ? AND artist = ?
                ''', (title, artist))
            else:
                self.cursor.execute('''
                    DELETE FROM user_songs WHERE title = ?
                ''', (title,))

            # Comprobar si alguna fila fue eliminada
            if self.cursor.rowcount > 0:
                self.connection.commit()  # Guardar los cambios en la base de datos
                return f"The song '{title}' has been removed from your library."
            else:
                return f"TThe song '{title}' was not found in your library"
        except sqlite3.Error as e:
            return f"Error al eliminar la canción de la base de datos: {e}"
        
        
    def clear_playlist(self) -> str:
        """Empty the playlist_songs table by removing all songs."""
        self.cursor.execute("DELETE FROM user_songs")
        self.connection.commit()
        return "Cleared all songs from the playlist."
    

    def recommend_songs(self, added_song_spotify_id):
        """
        Recommends 5 songs: 3 random songs from the same artist and 2 from the same genre.

        Args:
        added_song_spotify_id (str): The Spotify ID of the song that was just added.

        Returns:
        list: A list of 5 recommended song details (3 from the artist and 2 from the genre).
        """
        
        try:
            track = self.sp.track(added_song_spotify_id)
            artist_id = track['artists'][0]['id']
            artist_name = track['artists'][0]['name']
            artist = self.sp.artist(artist_id)

            genre = artist['genres'][0] if artist['genres'] else "pop"  # Si no tiene género, usamos "pop" como predeterminado

            # Same genre
            query_genre = f"genre:{genre}"
            results_genre = self.sp.search(q=query_genre, limit=50, type="track")
            genre_tracks = results_genre['tracks']['items']

            # Same artist
            query_artist = f"artist:{artist_name}"
            results_artist = self.sp.search(q=query_artist, limit=50, type="track")
            artist_tracks = results_artist['tracks']['items']

            # Select 3 random songs from the same artist
            random_artist_tracks = random.sample(artist_tracks, 3)

            # Select 2 random songs of the same genre
            random_genre_tracks = random.sample(genre_tracks, 2)

            recommended_songs = []

            for track in random_artist_tracks:
                recommended_song = {
                    'spotify_id': track['id'],
                    'title': track['name'],
                    'artist': ', '.join(artist['name'] for artist in track['artists']),
                    'album': track['album']['name'],
                    'release_date': track['album']['release_date'],
                }
                recommended_songs.append(recommended_song)

            for track in random_genre_tracks:
                recommended_song = {
                    'spotify_id': track['id'],
                    'title': track['name'],
                    'artist': ', '.join(artist['name'] for artist in track['artists']),
                    'album': track['album']['name'],
                    'release_date': track['album']['release_date'],
                }
                recommended_songs.append(recommended_song)

            return recommended_songs
        
        except spotipy.exceptions.SpotifyException as e:
            print(f"Error de Spotify: {e}")
            return []
        
#-------------------------------------------------------------------------------------------------------------------------------------------------

    def predict_intent(self, message):
        bert = self.predict_bert_intent(message)
        roberta = self.predict_roberta_intent(message)
        t5 = self.predict_t5_intent(message)
        
        intent_counts = Counter([bert, roberta, t5])
        
        if intent_counts.most_common(1)[0][1] > 1:
            return intent_counts.most_common(1)[0][0]
        else:
            return "unknown"
    
    def predict_bert_intent(self, message):

        # Tokenizar las frases de prueba
        inputs = self.bert_tokenizer(message, padding=True, truncation=True, return_tensors="pt")

        # Pasar las frases al modelo para obtener predicciones
        self.bert_model.eval()  # Poner el modelo en modo evaluación
        with torch.no_grad():
            outputs = self.bert_model(**inputs)

        # Convertir los logits a predicciones
        prediccion = torch.argmax(outputs.logits, dim=1).numpy()

        # Mapear los índices a las etiquetas
        etiquetas = ["add", "clear", "remove", "view"]
        
        return etiquetas[prediccion[0]]
    
    def predict_roberta_intent(self, message):

        # Tokenizar las frases de prueba
        inputs = self.roberta_tokenizer(message, padding=True, truncation=True, return_tensors="pt")

        # Pasar las frases al modelo para obtener predicciones
        self.roberta_model.eval()  # Poner el modelo en modo evaluación
        with torch.no_grad():
            outputs = self.roberta_model(**inputs)

        # Convertir los logits a predicciones
        prediccion = torch.argmax(outputs.logits, dim=1).numpy()

        # Mapear los índices a las etiquetas
        etiquetas = ["add", "clear", "remove", "view"]
        
        return etiquetas[prediccion[0]]
    
    def predict_t5_intent(self, message):

        # Preprocesar la entrada
        input_ids = self.t5_tokenizer("Clasifica: " + message, return_tensors="pt").input_ids
        attention_mask = self.t5_tokenizer("Clasifica: " + message, return_tensors="pt").attention_mask

        # Generar la predicción
        self.t5_model.eval()
        with torch.no_grad():
            generated_ids = self.t5_model.generate(input_ids=input_ids, attention_mask=attention_mask, max_length=10)

        # Decodificar la predicción
        decoded_pred = self.t5_tokenizer.decode(generated_ids[0], skip_special_tokens=True)
        
        return decoded_pred
    
    def suggest_unused_feature(self, used_features: dict):
        """
        This function suggests unused features to the user based on their interaction with the system.
        
        Args:
        used_features (dict): A dictionary that keeps track of the features the user has already used.

        Returns:
        str: A suggestion for an unused feature or None if all features have been used.
        """
        suggestions = []
        if not used_features.get("song_added", False):
            suggestions.append("You can add songs to your playlist by typing 'add <song>' or 'add <artist>: <song>' or 'add <song> by <artist>'.")
        if not used_features.get("playlist_viewed", False):
            suggestions.append("Want to see your playlist? Just use 'view playlist'.")
        if not used_features.get("song_removed", False):
            suggestions.append("If you'd like to remove a song from your playlist, you can use 'remove <song>' or 'remove <artist>: <song>' or 'remove <song> by <artist>'.")
        if not used_features.get("playlist_cleared", False):
            suggestions.append("Need to start fresh? You can clear the playlist with 'clear playlist'.")
        
        # Introduce only one feature at a time to avoid overwhelming the user
        if suggestions:
            return suggestions[0]
        return None

    def normalize_input(self, text):
        """Normalize the input by converting it to lowercase and removing punctuation."""
        
        # Remove unnecessary punctuation (except for valid cases like apostrophes in titles)
        text = re.sub(r'[^\w\s,.]', '', text)  # Keeps only word characters, spaces, ',' and '.'
        
        return text
    
    def extract_song_info(self, text, intent):
        if intent in ["add", "remove"]:
            match = re.search(r"(?:add|ad|addd|adddd|remove|include|inclde|includ|delete|dlte|delet|erase|eras|deletee|take)\s+([^:]+?)\s*(?:by|from|:)\s*(.+?)(?:\s+to|\s+from|\s+on|\s+in|s+of|\s+my|\s+mi|\s+playlist|\s+playlis|\s+list|\s+.|$)", text, re.IGNORECASE)

            if match:
                title = match.group(1).strip()
                artist = match.group(2).strip()
                return title, artist

            match_title_only = re.search(r"(?:add|ad|addd|adddd|remove|include|inclde|includ|delete|dlte|delet|erase|eras|deletee|take)\s+(.+?)(?:\s+to|\s+from|\s+on|\s+in|\s+my|\s+mi|\s+playlist|\s+playlis|\s+list|\s+.|$)", text, re.IGNORECASE)
            if match_title_only:
                title = match_title_only.group(1).strip()
                artist = None  # Artista no proporcionado
                return title, artist

        return None, None

# Inicia la plataforma Flask con tu nuevo agente
platform = FlaskSocketPlatform(MusicRecommenderAgent)
platform.start()
