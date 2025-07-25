import json
import hashlib
import time


class Block:
    def __init__(self, index, transactions, previous_hash, nonce=0):
        self.index = index  # Position of the block in the chain
        self.timestamp = time.time()  # Time when the block is created
        self.transactions = transactions  # List of Transaction objects
        self.previous_hash = previous_hash  # Hash of the previous block
        self.nonce = nonce  # Nonce value used for mining (proof of work)
        self.hash = self.compute_hash()  # Hash of the current block

    def compute_hash(self):
        """
        Compute the SHA-256 hash of the block header.

        Steps:
        - Serialize the transactions:
            - Convert each transaction in self.transactions to a dictionary using the to_dict() method.
            - Create a list of these serialized transactions.
            - Convert the list of transactions to a JSON string with sorted keys.
        - Create a dictionary containing the block header fields:
            - index
            - timestamp
            - previous_hash
            - transactions (the serialized transactions)
            - nonce
            - [IMPORTANT] please follow the same key names
                block_header = {
                    "index": "",
                    "timestamp": "",
                    "previous_hash": "",
                    "transactions": "",
                    "nonce": "",
                }
        - Convert the block header dictionary to a JSON string with sorted keys.
        - Encode the JSON string to bytes (utf-8).
        - Compute the SHA-256 hash of the bytes.
        - Return the hexadecimal digest of the hash.
        """
        # TODO: Implement the block hashing logic
        
        # Step 1: Serialize the transactions
        serialized_transactions = [tx.to_dict() for tx in self.transactions]  # List of dicts
        transactions_json = json.dumps(serialized_transactions, sort_keys=True)  # Convert list to JSON string
        
        # Step 2: Create the block header
        block_header = {
            "index": self.index,
            "timestamp": self.timestamp,
            "previous_hash": self.previous_hash,
            "transactions": transactions_json,  # Use the serialized transactions
            "nonce": self.nonce,
        }
        
        # Step 3: Convert block header to JSON string
        block_header_string = json.dumps(block_header, sort_keys=True)
        
        # Step 4: Encode the JSON string to bytes
        block_header_bytes = block_header_string.encode('utf-8')
        
        # Step 5: Compute SHA-256 hash
        block_hash = hashlib.sha256(block_header_bytes).hexdigest()
        
        # Return the computed hash
        return block_hash

    def has_transaction(self, transaction):

        transaction_dict = transaction.to_dict()

        for tx in self.transactions:
            if tx.to_dict() == transaction_dict:
                return True
        return False

    def __str__(self):
        return (
            f"Block(index={self.index}, "
            f"timestamp={self.timestamp}, "
            f"transactions={len(self.transactions)}, "
            f"hash={self.hash[:10]}..., "
            f"prev_hash={self.previous_hash[:10]}..., "
            f"nonce={self.nonce})"
        )
