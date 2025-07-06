import time
from typing import List, Tuple
from task1 import caesar_cipher, clean_text, generate_transposition_key, get_caesar_key, transposition_cipher

def encryption(text, transposition_key, caesar_key):
    """
    Encrypts the given text using a combination of Caesar cipher and transposition cipher.

    Parameters:
    - text: The plain text to be encrypted.
    - transposition_key: The key used for the transposition cipher.
    - caesar_key: The shift value used for the Caesar cipher.
    
    Returns:
    - The encrypted text after applying both the Caesar cipher and the transposition cipher.
    """

    text = caesar_cipher(text, caesar_key)
    encrypted_text = transposition_cipher(text, transposition_key)
    
    return encrypted_text

def calculate_bit_difference(text1, text2) -> float:
    """
    Calculates the bit-level difference between two strings of text.
    
    Parameters:
    - text1: The first string of text.
    - text2: The second string of text.
    
    Returns:
    - A float representing the percentage of differing bits between the two texts.
    """
    
    diff = sum(tx1 != tx2 for tx1, tx2 in zip(text1, text2)) # Count the number of different bits
    return (diff / len(text1)) * 100

def evaluate_avalanche_effect(original_text, modified_text, transposition_key,caesar_key, rounds: int) -> List[Tuple[int, float, float]]:
    """
    Performs repeated avalanche effect analysis on the original and modified text.
    
    Parameters:
    - original_text: The original input text before modification.
    - modified_text: The modified input text for analysis.
    - transposition_key: The key used for the transposition cipher.
    - caesar_key: The shift value used for the Caesar cipher.
    - rounds: The number of rounds to perform the analysis.
    
    Returns:
    - A list of tuples containing:
        - The round number.
        - The calculated avalanche effect for each round (percentage).
        - The time elapsed since the beginning of the analysis.
    """

    results = []
    previous_text = original_text
    current_text = modified_text

    # Track the start time
    start_time = time.time()

    for round_num in range(1, rounds + 1):
        # Encrypt the text Caesar - Transposition cipher
        encrypted_prev_text = encryption(previous_text, transposition_key, caesar_key)
        encrypted_current_text = encryption(current_text, transposition_key, caesar_key)
        
        # Calculate avalanche effect
        avalanche_effect = calculate_bit_difference(encrypted_prev_text, encrypted_current_text)
        # Store results
        elapsed_time = time.time() - start_time
        results.append((round_num, avalanche_effect, elapsed_time))

        # Prepare for next round
        previous_text = encrypted_prev_text
        current_text = encrypted_current_text

    print("\nRepeated Avalanche Effect Analysis Results:")
    for round_num, avalanche_effect, elapsed_time in results:
        print(f"Round {round_num}: Avalanche Effect = {avalanche_effect:.3f}% | Time Elapsed = {elapsed_time} seconds")

if __name__ == "__main__":
    
        print("\n*** Task 2: Analyze the Avalanche effect ***")
        original_text = input("Introduce the original text: ")
        original_text = clean_text(original_text)
        modified_text = input("Introduce the modified text: ")
        modified_text = clean_text(modified_text)
        phone_number = input("Introduce the last five digits of your phone number: ")
        caesar_key = get_caesar_key(phone_number)
        transposition_key = generate_transposition_key(phone_number)
        rounds = 16

        evaluate_avalanche_effect(original_text, modified_text, transposition_key, caesar_key, rounds)
        
    