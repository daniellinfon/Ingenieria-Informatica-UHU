from ecdsa import SigningKey, SECP256k1
import binascii

import ecdsa
from transaction import Transaction 

class Wallet:
    def __init__(self):
        self.private_key = self.generate_private_key()  # Hexadecimal private key
        self.public_key = self.get_public_key(self.private_key)  # Hexadecimal public key
        self.address = self.public_key  # Simplified address (using public key)

    def __str__(self):
        return (
            f"Wallet(address={self.address[:10]}..., "
            f"public_key={self.public_key[:10]}..., "
            f"private_key=****)"
        )

    @staticmethod
    def generate_private_key():
        """
        Generate a new ECDSA private key.

        - Use the ecdsa library to generate a new SigningKey using the SECP256k1 curve.
        - Convert the private key to bytes using to_string().
        - Convert the bytes to a hex string.
        - Return the hex string representation (utf-8) of the private key.
        """
        private_key = SigningKey.generate(curve=SECP256k1)  # Generate private key
        private_key_hex = binascii.hexlify(private_key.to_string()).decode('utf-8')
        return private_key_hex

    @staticmethod
    def get_public_key(private_key_hex):
        """
        Derive the public key from the private key.

        - Convert the private key from hex to bytes.
        - Create a SigningKey object from the private key bytes.
        - Get the verifying key (public key) from the signing key.
        - Convert the public key to bytes using to_string().
        - Convert the bytes to a hex string.
        - Return the hex string representation (utf-8) of the public key.
        """
        private_key_bytes = binascii.unhexlify(private_key_hex)  # Convert private key from hex to bytes
        private_key = SigningKey.from_string(private_key_bytes, curve=SECP256k1)  # Create SigningKey
        public_key = private_key.verifying_key  # Get public key
        public_key_hex = binascii.hexlify(public_key.to_string()).decode('utf-8')
        return public_key_hex

    def create_transaction(self, recipient_address, amount):
        """
        Create and sign a new transaction.

        - Create a Transaction object with the sender's address, recipient's address, and amount.
        - Include the sender's public key in the transaction.
        - Sign the transaction using the sender's private key.
        - Return the signed transaction.
        """
        # Create the transaction object
        transaction = Transaction(
            sender_address=self.address,  # Sender's wallet address
            recipient_address=recipient_address,  # Recipient's wallet address
            amount=amount,  # Amount to transfer
            sender_public_key=self.public_key  # Sender's public key
        )
        
        # Sign the transaction
        transaction.compute_hash()  # Compute the hash of the transaction
        transaction.sign_transaction(self.private_key)  # Sign the transaction using the sender's private key
        
        # Return the signed transaction
        return transaction
        
        
