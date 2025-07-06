import os
import time
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

from task1 import diffie_hellman
from task2 import hmac_with_sha256

def encrypt_CTR(plaintext, key):
    """
    Encrypts the given plaintext using AES encryption in CTR (Counter) mode.

    Parameters:
    plaintext (str): The message to be encrypted.
    key (bytes): The secret key used for AES encryption (16, 24, or 32 bytes long).

    Returns:
    tuple: A tuple containing the nonce (used for CTR mode) and the ciphertext (encrypted message).
    """
    # Generates a random 16-byte nonce
    nonce = os.urandom(16)

    # Creates an encryption object using the AES algorithm in CTR mode
    cipher = Cipher(algorithms.AES(key), modes.CTR(nonce), backend=default_backend())
    encryptor = cipher.encryptor()

    # Encrypt the plaintext and get the ciphertext
    ciphertext = encryptor.update(plaintext.encode('utf-8')) + encryptor.finalize()
    
    return nonce, ciphertext

def decrypt_CTR(ciphertext, key, nonce):
    """
    Decrypts the ciphertext using AES in CTR mode with the provided nonce and key.

    Parameters:
    ciphertext (bytes): The encrypted message.
    key (bytes): The secret key for AES encryption (16, 24, or 32 bytes).
    nonce (bytes): The 16-byte nonce used during encryption.

    Returns:
    str: The decrypted plaintext message.
    """
    # Creates a decryption object using the AES algorithm in CTR mode
    cipher = Cipher(algorithms.AES(key), modes.CTR(nonce), backend=default_backend())
    decryptor = cipher.decryptor()

    # Decrypt the ciphertext
    plaintext = decryptor.update(ciphertext) + decryptor.finalize()

    return plaintext.decode('utf-8')  # Return the decoded plaintext

if __name__ == "__main__":

    p = 23456
    g = 5
    key = diffie_hellman(p, g)

    # *** WITH SHA-256 HASH ***
    # Alice send a message to Bob and she will compute the HMAC of the message using the shared key
    message = 'Hello, Bob!'
    print('Alice sends the next message:', message)
    hmac_tag = hmac_with_sha256(key, message)
    print('HMAC tag with SHA-256 hash:', hmac_tag)  
    # Alice concatenate both the message and the HMAC tag and encrypts it
    plaintext = message + hmac_tag

    aes_key = os.urandom(32)
    nonce, ciphertext = encrypt_CTR(plaintext, aes_key)

    # Output the encrypted message and nonce
    print(f"Encrypted message: {ciphertext}")
    print(f"Nonce: {nonce}\n")

    # Bob receives the encrypted message sent by Alice. He decrypt it to get the message and the HMAC tag
    decrypted_message_with_hmac = decrypt_CTR(ciphertext, aes_key, nonce)
    print('Bob receives the Encrypted message and deciphers it:', decrypted_message_with_hmac)

    # Separate the original message and HMAC
    original_message = decrypted_message_with_hmac[:-64]  # Assuming HMAC is 64 hex characters (32 bytes)
    received_hmac_tag = decrypted_message_with_hmac[-64:]  # The last part is the HMAC

    print(f"Original Message: {original_message}")
    print(f"Received HMAC tag: {received_hmac_tag}")

    # Bob uses the same shared secret key to compute the HMAC of the received message:
    computed_hmac = hmac_with_sha256(key, original_message) 

    if computed_hmac == received_hmac_tag:
        print("Message authenticated and verified as untampered.")
    else:
        print("Message authentication failed.")



