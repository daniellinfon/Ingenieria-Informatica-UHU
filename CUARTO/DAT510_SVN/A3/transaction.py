import hashlib
import binascii
import json
import ecdsa


# Transaction Class with Digital Signature
class Transaction:
    def __init__(
        self,
        sender_address,
        recipient_address,
        amount,
        sender_public_key=None,
        signature=None,
    ):
        self.sender_address = (
            sender_address  # Address of the sender (public key or wallet address)
        )
        self.recipient_address = recipient_address  # Address of the recipient
        self.amount = amount  # Amount to transfer
        self.sender_public_key = sender_public_key  # Sender's public key (hex string)
        self.signature = signature  # Digital signature (hex string)

    def __str__(self):
        return (
            f"Transaction(sender={self.sender_address[:10]}..., "
            f"recipient={self.recipient_address[:10]}..., "
            f"amount={self.amount})"
        )

    def to_dict(self):
        """
        Convert the transaction details to a dictionary format.
        This is useful for serialization and hashing.
        """
        return {
            "sender_address": self.sender_address,
            "recipient_address": self.recipient_address,
            "amount": self.amount,
        }

    def compute_hash(self):
        """
        Compute the SHA-256 hash of the transaction.

        Steps:
        - Convert the transaction dictionary to a JSON string with sorted keys.
        - Encode the string to bytes.
        - Compute the SHA-256 hash of the bytes.
        - Return the hexadecimal digest of the hash.
        """
        transaction_string = json.dumps(self.to_dict(), sort_keys=True)
        transcation_encoded = transaction_string.encode("utf-8")
        transaction_sha256 = hashlib.sha256(transcation_encoded)
        return transaction_sha256.hexdigest()

    def sign_transaction(self, private_key):
        """
        Sign the transaction using the sender's private key.

        Steps:
        - Compute the hash of the transaction (self.compute_hash()).
        - Convert the private key from hex to bytes.
        - Create a SigningKey object using the private key and SECP256k1 curve.
        - Sign the transaction hash (utf-8) using deterministic ECDSA signing.
        - Convert the signature to a hex string (utf-8) and store it in self.signature.

        Note:
        - Use the sign_deterministic method to ensure consistent signatures for testing.
        """
        # Compute the transaction hash
        transaction_hash = self.compute_hash()
        transaction_hash_bytes = transaction_hash.encode("utf-8")  # Convert hash to bytes
        
        # Convert private key from hex to bytes
        private_key_bytes = binascii.unhexlify(private_key)
        
        # Create a SigningKey object
        signing_key = ecdsa.SigningKey.from_string(private_key_bytes, curve=ecdsa.SECP256k1)
        
        # Sign the transaction hash
        signature = signing_key.sign_deterministic(transaction_hash_bytes)
        
        # Convert the signature to hex and store it
        self.signature = binascii.hexlify(signature).decode("utf-8")

    def is_valid(self):
        """
        Verify the transaction signature.

        Steps:
        - Check if the transaction is a mining reward (sender_address == "0").
          If so, return True.
        - Ensure that both the signature and sender's public key are present.
          If not, return False.
        - Convert the sender's public key and signature from hex to bytes.
        - Create a VerifyingKey object using the public key and SECP256k1 curve.
        - Compute the hash of the transaction.
        - Verify the signature using the verifying key and transaction hash (utf-8).
        - Return True if the signature is valid; otherwise, return False.

        Exception Handling:
        - If any error occurs during verification (e.g., invalid signature format),
          catch the exception and return False.
        """
        # Check if it's a mining reward (no sender)
        if self.sender_address == "0":
            return True

        # Ensure the public key and signature are present
        if not self.signature or not self.sender_public_key:
            return False

        try:
            # Convert public key and signature from hex to bytes
            public_key_bytes = binascii.unhexlify(self.sender_public_key)
            signature_bytes = binascii.unhexlify(self.signature)

            # Create a VerifyingKey object
            verifying_key = ecdsa.VerifyingKey.from_string(public_key_bytes, curve=ecdsa.SECP256k1)

            # Compute the transaction hash
            transaction_hash = self.compute_hash()
            transaction_hash_bytes = transaction_hash.encode("utf-8")

            # Verify the signature
            return verifying_key.verify(signature_bytes, transaction_hash_bytes)

        except Exception as e:
            # If any error occurs during verification, return False
            print(f"Verification error: {e}")
            return False
