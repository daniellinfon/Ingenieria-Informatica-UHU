import os
import time
import matplotlib.pyplot as plt
from typing import List, Tuple
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
from task1 import caesar_cipher, clean_text, generate_transposition_key, get_caesar_key, transposition_cipher
from task2 import calculate_bit_difference
from task4 import plot_results, vigenere_autokey_encrypt

def encrypt_CTR(plaintext, key):
    # Generates a random 16-byte nonce
    nonce = os.urandom(16)

    # Creates an encryption object using the AES algorithm in CTR mode
    cipher = Cipher(algorithms.AES(key), modes.CTR(nonce), backend=default_backend())
    encryptor = cipher.encryptor()

    # Encrypt the plaintext and get the ciphertext
    ciphertext = encryptor.update(plaintext.encode('utf-8')) + encryptor.finalize()
    
    return nonce, ciphertext


def encryption(text, transposition_key, caesar_key, aes_key, autokey=''):
    """Encrypt the plain text."""
    text = caesar_cipher(text, caesar_key)
    text = transposition_cipher(text, transposition_key)
    
    if autokey != '':
        text = vigenere_autokey_encrypt(text, autokey)
    
    nonce, ctr_encrypted = encrypt_CTR(text, aes_key)
    
    return nonce, ctr_encrypted

def evaluate_avalanche_effect(original_text, modified_text, transposition_key,caesar_key, rounds: int, 
                               autokey='') -> List[Tuple[int, float, float]]:
    
    """Performs repeated avalanche effect analysis on the initial text."""
    results = []
    previous_text = original_text
    current_text = modified_text 

    option = input("Do you want to add vigenere autokey system to encryption? (y/n): ")

    if(option == 'y'):
        autokey = input("Introduce the key for Vigenère Autokey System: ")

    # Track the start time
    start_time = time.time()

    for round_num in range(1, rounds + 1):
        # Encrypt the text
        aes_key = os.urandom(32)
        nonce, encrypted_original_text = encryption(previous_text, transposition_key, caesar_key, aes_key, autokey)
        nonce, encrypted_modified_text = encryption(current_text, transposition_key, caesar_key,aes_key, autokey)

        # Calculate avalanche effect
        avalanche_effect = calculate_bit_difference(encrypted_original_text, encrypted_modified_text)
        #print(previous_ciphertext)
        #print(encrypted_text)
       
        # Store results
        elapsed_time = time.time() - start_time
        results.append((round_num, avalanche_effect, elapsed_time))

        # Prepare for next round
        previous_text = encrypted_original_text
        current_text = encrypted_modified_text

    print("\nRepeated Avalanche Effect Analysis Results:")
    for round_num, avalanche_effect, elapsed_time in results:
        print(f"Round {round_num}: Avalanche Effect = {avalanche_effect:.3f}% | Time Elapsed = {elapsed_time:.4f} seconds")

    plot_results(results)

# Ejemplo de uso
if __name__ == "__main__":

    print("\n*** Task 5: Enhance Security with Block Ciphers ***")
    original_text = input("Introduce the original text: ")
    original_text = clean_text(original_text)
    modified_text = input("Introduce the modified text: ")
    modified_text = clean_text(modified_text)
    phone_number = input("Introduce the last five digits of your phone number: ")
    caesar_key = get_caesar_key(phone_number)
    transposition_key = generate_transposition_key(phone_number)
    rounds = 14

    evaluate_avalanche_effect(original_text, modified_text, transposition_key, caesar_key, rounds)


