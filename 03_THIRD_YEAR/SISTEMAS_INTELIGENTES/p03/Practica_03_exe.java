package si2024.daniellinfonyealu.p03;

import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_03_exe {
	/*
	 * public static void main(String[] args) {
	 * 
	 * String p0 = "si2024.daniellinfonyealu.p03.Practica_03_Agente";
	 * 
	 * //Load available games String spGamesCollection =
	 * "examples/all_games_sp.csv"; String[][] games =
	 * Utils.readGames(spGamesCollection);
	 * 
	 * //Game settings boolean visuals = true; int seed = new Random().nextInt();
	 * 
	 * // Game and level to play int gameIdx = 53; int levelIdx = 2; // level names
	 * from 0 to 4 (game_lvlN.txt).
	 * 
	 * String gameName = games[gameIdx][1]; String game = games[gameIdx][0]; String
	 * level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
	 * 
	 * 
	 * // 1. This starts a game, in a level, played by a human.
	 * //ArcadeMachine.playOneGame(game, level1, null, seed);
	 * 
	 * // 2. This plays a game in a level by the controller. /* double [] array;
	 * double score=0; int intentos = 10; for(int i=0;i<intentos;i++) { array =
	 * ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0); score =
	 * score + array[1]; } System.out.println(" PUNTUACION MEDIA: "+score/intentos);
	 * 
	 * ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
	 * 
	 * System.exit(0);
	 * 
	 * }
	 */

	public static double test_level(int lvl) {

		String p0 = "si2024.daniellinfonyealu.p03.Practica_03_Agente";
		// Load available games
		String spGamesCollection = "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);
		int gameIdx = 53;
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + lvl);
		Random rng = new Random();
		// ArcadeMachine.playOneGame(game, level1, null, 33);

		int ganadas = 0;
		int score = 0;
		for (int i = 0; i < 1; i++) {
			int seed = rng.nextInt();
			double[] cosos = ArcadeMachine.runOneGame(game, level1, false, p0, null, seed, 0);
			if (cosos[0] != 0.0) {
				score += (int) cosos[1];
				ganadas++;
			} else {
			}
		}
		System.out.println("WR en el nivel " + lvl + ": " + ganadas + "%");
		System.out.println("Sc en el nivel " + lvl + ": " + score / 1);
		return score;
	}

	public static void main(String[] args) {

		int total_points = 0;
		total_points = 0;
		for (int i = 0; i < 5; i++) {
			total_points += (int) test_level(i);
			System.out.println("\n");
		}
		System.out.println("Media: " + total_points);
		System.exit(0);

	}
}
