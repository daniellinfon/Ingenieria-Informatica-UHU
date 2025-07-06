import os
import hmac
import hashlib
import random
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

from task2 import hmac_with_sha256
from task3 import decrypt_CTR, encrypt_CTR

# Function to compute shared secret based on public and private keys
def compute_shared_secret(public_key, private_key, p):
    return pow(public_key, private_key, p)

def generate_keys(p,g):
    private_key = random.randint(1, p-1)
    public_key = pow(g, private_key, p)

    return private_key, public_key
    


# Double Ratchet class
class DoubleRatchet:
    def __init__(self, p, g, initial_private_key, initial_public_key, peer_public_key):
        self.p = p
        self.g = g
        self.private_key = initial_private_key
        self.public_key = initial_public_key
        self.peer_public_key = peer_public_key

        # Initial shared secret and root key
        self.shared_secret = compute_shared_secret(self.peer_public_key, self.private_key, self.p)
        self.root_key = hmac_with_sha256('root', self.shared_secret)

        # Initialize chain keys for sending and receiving
        self.chain_key_send = hmac_with_sha256(self.root_key, 'send')
        self.chain_key_recv = hmac_with_sha256(self.root_key, 'receive')

    # Diffie-Hellman ratchet to exchange new keys and derive a new root key
    def dh_ratchet(self, private_key, public_key, peer_public_key):
        # Generate new DH secret key
        self.peer_public_key = peer_public_key
        self.private_key = private_key
        self.public_key = public_key
        self.shared_secret = compute_shared_secret(self.peer_public_key, self.private_key, self.p)

        # Update the root key
        self.root_key = hmac_with_sha256(self.root_key, self.shared_secret)
        print(f"New root key: {self.root_key}")

        # Reset chain keys
        self.chain_key_send = hmac_with_sha256(self.root_key, 'send')
        self.chain_key_recv = hmac_with_sha256(self.root_key, 'receive')

    # Symmetric ratchet to derive the next message key
    def symmetric_ratchet_send(self):
        self.chain_key_send = hmac_with_sha256(self.chain_key_send, 'ratchet')
        message_key = hmac_with_sha256(self.chain_key_send, 'message')
        # print(f"Derived message key (send): {message_key}")
        return message_key

    def symmetric_ratchet_recv(self):
        self.chain_key_recv = hmac_with_sha256(self.chain_key_recv, 'ratchet')
        message_key = hmac_with_sha256(self.chain_key_recv, 'message')
        # print(f"Derived message key (receive): {message_key}")
        return message_key
    
    def set_chain_keys(self, send_key, recieve_key):
        self.chain_key_send = send_key
        self.chain_key_recv = recieve_key

if __name__ == "__main__":

    # Example usage of Double Ratchet between Alice and Bob
    p = 3079  # Prime number for Diffie-Hellman
    g = 2     # Generator for Diffie-Hellman

    # Initial key pairs for Alice and Bob
    alice_private_key, alice_public_key = generate_keys(p,g)
    bob_private_key, bob_public_key = generate_keys(p,g)

    # Initialize Double Ratchet
    alice_ratchet = DoubleRatchet(p, g, alice_private_key, alice_public_key, bob_public_key)
    bob_ratchet = DoubleRatchet(p, g, bob_private_key, bob_public_key, alice_public_key)

    # Synchronize keys
    bob_ratchet.set_chain_keys(alice_ratchet.chain_key_recv, alice_ratchet.chain_key_send)

    # Simulate multiple rounds of message exchange
    messages = ["Hello, Bob!", "How are you?", "Hello, Alice!", "Fine, I am doing the A2",  "Oh, I see", "Then we talk later, Bye!"]
    restart = 0

    for round_num, message in enumerate(messages, 1):

        # Every 2 rounds, Alice and Bob wil each generate a new Diffie-Hellman key pair and exchange their new DH public keys.
        restart += 1
        if restart == 3:
            alice_private_key, alice_public_key = generate_keys(p,g)
            bob_private_key, bob_public_key = generate_keys(p,g)
            print('\nAlice and Bob generate new DH key pairs')
            print('\nAlice:')
            alice_ratchet.dh_ratchet(alice_private_key, alice_public_key, bob_public_key)
            print(f"New chain key send: {alice_ratchet.chain_key_send}")
            print(f"New chain key receive: {alice_ratchet.chain_key_recv}")

            print('\nBob:')
            bob_ratchet.dh_ratchet(bob_private_key, bob_public_key, alice_public_key)
            
             # Synchronize keys
            bob_ratchet.set_chain_keys(alice_ratchet.chain_key_recv, alice_ratchet.chain_key_send)
            print(f"New chain key send: {bob_ratchet.chain_key_send}")
            print(f"New chain key receive: {bob_ratchet.chain_key_recv}")
            restart = 0

        print(f"\n--- Round {round_num} ---")
        if round_num == 3 or round_num == 4:
             # Bob sends the message
            print(f"\nBob sends message {round_num}: {message}")
            bob_message_key = bob_ratchet.symmetric_ratchet_send()
            bob_message_key_bytes = bytes.fromhex(bob_message_key)
            hmac_tag = hmac_with_sha256(bob_ratchet.chain_key_send, message)

            # Concatenate both the message and the HMAC tag and encrypts it
            plaintext = message + hmac_tag
            nonce, ciphertext = encrypt_CTR(plaintext, bob_message_key_bytes)
            
            print("HMAC tag:", hmac_tag)
            print("Bob Send Chain Key:", bob_ratchet.chain_key_send)

            # Bob receives and decrypts the message
            print(f"\nAlice receives message {round_num}")
            alice_message_key = alice_ratchet.symmetric_ratchet_recv()
            alice_message_key_bytes = bytes.fromhex(alice_message_key)
            
            print("Alice Receive Chain Key:", alice_ratchet.chain_key_recv)
            decrypted_message = decrypt_CTR(ciphertext, alice_message_key_bytes, nonce)
            print(f"Decrypted message from Bob: {decrypted_message}")

            # Separate the original message and HMAC
            original_message = decrypted_message[:-64]  # Assuming HMAC is 64 hex characters (32 bytes)
            received_hmac_tag = decrypted_message[-64:]  # The last part is the HMAC

            print(f"Original Message: {original_message}")
            print(f"Received HMAC tag: {received_hmac_tag}")
        else:
            # Alice sends the message
            print(f"\nAlice sends message {round_num}: {message}")
            alice_message_key = alice_ratchet.symmetric_ratchet_send()
            alice_message_key_bytes = bytes.fromhex(alice_message_key)
            hmac_tag = hmac_with_sha256(alice_ratchet.chain_key_send, message)

            # Concatenate both the message and the HMAC tag and encrypts it
            plaintext = message + hmac_tag
            nonce, ciphertext = encrypt_CTR(plaintext, alice_message_key_bytes)
            
            print("HMAC tag:", hmac_tag)
            print("Alice Send Chain Key:", alice_ratchet.chain_key_send)

            # Bob receives and decrypts the message
            print(f"\nBob receives message {round_num}")
            bob_message_key = bob_ratchet.symmetric_ratchet_recv()
            bob_message_key_bytes = bytes.fromhex(bob_message_key)
            
            print("Bob Receive Chain Key:", bob_ratchet.chain_key_recv)
            decrypted_message = decrypt_CTR(ciphertext, bob_message_key_bytes, nonce)
            print(f"Decrypted message from Alice: {decrypted_message}")

            # Separate the original message and HMAC
            original_message = decrypted_message[:-64]  # Assuming HMAC is 64 hex characters (32 bytes)
            received_hmac_tag = decrypted_message[-64:]  # The last part is the HMAC

            print(f"Original Message: {original_message}")
            print(f"Received HMAC tag: {received_hmac_tag}")
