import time
import matplotlib.pyplot as plt
from typing import List, Tuple
from task1 import caesar_cipher, clean_text, generate_transposition_key, get_caesar_key, transposition_cipher
from task2 import calculate_bit_difference

def vigenere_autokey_encrypt(plaintext, key):
    """
    Encrypts the given plaintext using the Vigenère Autokey Cipher.
    
    Parameters:
    - plaintext: The original text to be encrypted.
    - key: The initial key for the Vigenère Autokey system.
    
    Returns:
    - The encrypted text (ciphertext).
    """

    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    key = key.upper()
    plaintext = plaintext.upper()

    # Extend the key with the original text
    extended_key = key + plaintext
    extended_key = extended_key[:len(plaintext)]  # The length of the extended key is equal to that of the text
    
    ciphertext = []

    for i, char in enumerate(plaintext):
        char = str(char)
        if char.isalpha():
            # Get the index of the current letter in the plaintext and the key
            text_index = alphabet.index(char)
            key_index = alphabet.index(extended_key[i])

            # Update alphabet according to key_index           
            alphabet_i = alphabet[key_index:] + alphabet

            # Find the new cipher char
            ciphertext.append(alphabet_i[text_index])
        else:
            ciphertext.append(char)

    return ''.join(ciphertext)


def encryption(text, transposition_key, caesar_key, autokey):
    """
    Encrypts the text using a combination of Caesar Cipher, Transposition Cipher, and Vigenère Autokey Cipher.
    
    Parameters:
    - text: The plain text to be encrypted.
    - transposition_key: The key used for the transposition cipher.
    - caesar_key: The shift value for the Caesar cipher.
    - autokey: The initial key for the Vigenère Autokey system.
    
    Returns:
    - The fully encrypted text after applying all three ciphers.
    """

    text = caesar_cipher(text, caesar_key)
    encrypted_text = transposition_cipher(text, transposition_key)
    encrypted_text = vigenere_autokey_encrypt(encrypted_text, autokey)

    return encrypted_text

def evaluate_avalanche_effect(original_text, modified_text, transposition_key,caesar_key, rounds: int, 
                               autokey) -> List[Tuple[int, float, float]]:
    """
    Performs repeated avalanche effect analysis on the input texts using encryption.
    
    Parameters:
    - original_text: The original input text.
    - modified_text: The modified text (slightly different from the original).
    - transposition_key: The key used for the transposition cipher.
    - caesar_key: The shift value for the Caesar cipher.
    - rounds: The number of rounds to perform the analysis.
    - autokey: The initial key for the Vigenère Autokey cipher.
    
    Returns:
    - A list of tuples containing:
        - The round number.
        - The avalanche effect for each round (percentage).
        - The time elapsed for each round.
    """

    results = []
    previous_text = original_text
    current_text = modified_text

    # Track the start time
    start_time = time.time()

    for round_num in range(1, rounds + 1):
        # Encrypt the text
        encrypted_prev_text = encryption(previous_text, transposition_key, caesar_key, autokey)
        encrypted_current_text = encryption(current_text, transposition_key, caesar_key, autokey)
        
        # Calculate avalanche effect
       
        avalanche_effect = calculate_bit_difference(encrypted_prev_text, encrypted_current_text)
        #print(previous_ciphertext)
        #print(encrypted_text)
       
        # Store results
        elapsed_time = time.time() - start_time
        results.append((round_num, avalanche_effect, elapsed_time))

        # Prepare for next round
        previous_text = encrypted_prev_text
        current_text = encrypted_current_text

    print("\nRepeated Avalanche Effect Analysis Results:")
    for round_num, avalanche_effect, elapsed_time in results:
        print(f"Round {round_num}: Avalanche Effect = {avalanche_effect:.3f}% | Time Elapsed = {elapsed_time:.4f} seconds")

    plot_results(results)

def plot_results(results):
    """
    Plots the results of the avalanche effect analysis, showing both the avalanche effect
    and the computation time as functions of the number of rounds.
    
    Parameters:
    - results: A list of tuples containing the round number, avalanche effect, and computation time.
    """

    rounds = [round_num for round_num, _, _ in results]
    avalanche_effects = [avalanche_effect for _, avalanche_effect, _ in results]
    times = [elapsed_time for _, _, elapsed_time in results]

    fig, ax1 = plt.subplots()

    color = 'tab:blue'
    ax1.set_xlabel('Number of Rounds')
    ax1.set_ylabel('Avalanche Effect (%)', color=color)
    ax1.plot(rounds, avalanche_effects, color=color, marker='o', label='Avalanche Effect')
    ax1.tick_params(axis='y', labelcolor=color)

    ax2 = ax1.twinx()
    color = 'tab:red'
    ax2.set_ylabel('Computation Time (seconds)', color=color)
    ax2.plot(rounds, times, color=color, marker='x', linestyle='--', label='Computation Time')
    ax2.tick_params(axis='y', labelcolor=color)

    fig.tight_layout()
    plt.title('Avalanche Effect and Computation Time vs. Number of Rounds')
    plt.show()


if __name__ == "__main__":

    print("\n*** Task 4: Optimize Avalanche Effect ***")
    original_text = input("Introduce the original text: ")
    original_text = clean_text(original_text)
    modified_text = input("Introduce the modified text: ")
    modified_text = clean_text(modified_text)
    phone_number = input("Introduce the last five digits of your phone number: ")
    caesar_key = get_caesar_key(phone_number)
    transposition_key = generate_transposition_key(phone_number)
    autokey = input("Introduce the key for Vigenère Autokey System: ")
    rounds = 16

    evaluate_avalanche_effect(original_text, modified_text, transposition_key, caesar_key, rounds, autokey)
