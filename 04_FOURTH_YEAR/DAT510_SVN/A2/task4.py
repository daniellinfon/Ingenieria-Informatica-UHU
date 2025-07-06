from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import os

from task1 import diffie_hellman
from task2 import hmac_with_sha256
from task3 import decrypt_CTR, encrypt_CTR


# Single Ratchet class
class SingleRatchet:
    def __init__(self, initial_chain_key):
        self.chain_key = initial_chain_key

    # Update the chain key (ratchet)
    def ratchet_chain_key(self):
        self.chain_key = hmac_with_sha256(self.chain_key, "ratchet")

    # Derive the message key
    def derive_message_key(self):
        return hmac_with_sha256(self.chain_key, "message")


if __name__ == "__main__":
    
    # Example usage of Single Ratchet between Alice and Bob
    p = 3079
    g = 2
    initial_chain_key = diffie_hellman(p,g)  # Initial chain key derived from Diffie-Hellman

    alice_ratchet = SingleRatchet(initial_chain_key)
    bob_ratchet = SingleRatchet(initial_chain_key)

    # Simulate multiple rounds of message exchange
    messages = ["Hello, Bob!", "How are you?", "Did you receive my message?", "See you soon!"]

    for round_num, message in enumerate(messages, 1):

        print(f"\n--- Round {round_num} ---")

        # Step 1: Alice ratchets her chain key and derives the message key
        print(f"\nAlice sends message {round_num}: {message}")
        alice_ratchet.ratchet_chain_key()
        hmac_tag = hmac_with_sha256(alice_ratchet.chain_key, message)
        print("HMAC tag:", hmac_tag)
        print("Alice Chain Key:", alice_ratchet.chain_key)

        alice_message_key = alice_ratchet.derive_message_key()
        alice_message_key_bytes = bytes.fromhex(alice_message_key)

        # Alice concatenate both the message and the HMAC tag and encrypts it
        plaintext = message + hmac_tag

        # Step 2: Alice encrypts the message with the derived key
        nonce, ciphertext = encrypt_CTR(plaintext, alice_message_key_bytes)

        # Step 3: Bob ratchets his chain key to synchronize with Alice and derives the message key
        print(f"\nBob receives message {round_num}")
        bob_ratchet.ratchet_chain_key()
        print("Bob Chain Key:", bob_ratchet.chain_key)
        bob_message_key = bob_ratchet.derive_message_key()
        bob_message_key_bytes = bytes.fromhex(bob_message_key)

        # Step 4: Bob decrypts the message received from Alice
        decrypted_message = decrypt_CTR(ciphertext, bob_message_key_bytes, nonce)
        print(f"\nDecrypted message from Alice: {decrypted_message}")

        # Separate the original message and HMAC
        original_message = decrypted_message[:-64]  # Assuming HMAC is 64 hex characters (32 bytes)
        received_hmac_tag = decrypted_message[-64:]  # The last part is the HMAC


        print(f"Original Message: {original_message}")
        print(f"Received HMAC tag: {received_hmac_tag}")
        
        

        # Bob uses the same shared secret key to compute the HMAC of the received message: 
        computed_hmac = hmac_with_sha256(alice_ratchet.chain_key, original_message)

        if computed_hmac == received_hmac_tag:
            print("\nMessage authenticated and verified as untampered.")
        else:
            print("Message authentication failed.")
