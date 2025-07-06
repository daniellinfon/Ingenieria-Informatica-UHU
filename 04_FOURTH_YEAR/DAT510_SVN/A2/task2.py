import hashlib

from task1 import diffie_hellman

def xor_hash(data, blocksize):
    """
    Generate a simple XOR hash for the given data.

    Parameters:
    data (bytes): The input data to be hashed.
    blocksize (int): The size of the block for the hash value.

    Returns:
    int: The XOR hash value.
    """
    hash_value = bytearray(blocksize)  # Initialize a bytearray with the specified block size
    
    # XOR each byte with the corresponding byte in hash_value
    for i in range(len(data)):
        hash_value[i % blocksize] ^= data[i]

    return bytes(hash_value)


def hmac_xor_hash(key, message):
    """
    HMAC implementation using simple XOR in Hashing.

    Parameters:
    key (int): The secret key for HMAC.
    message (str): The message to be authenticated.

    Returns:
    bytes: The generated authentication tag.
    """

    block_size = 64  # Block size

    # Convert the shared key integer and message to bytes
    key = str(key).encode()
    message = message.encode()

    # Step 1
    # If the key is longer than block size, hash it
    if len(key) > block_size:
        key = xor_hash(key, block_size)

    # If the key is shorter than block size, pad with zeros
    elif len(key) < block_size:
        key = key + b'\x00' * (block_size - len(key))

    # Define inner and outer padding
    ipad = bytes([0x36] * block_size)
    opad = bytes([0x5c] * block_size)

    # XOR the key with ipad and opad
    key_ipad = bytes([x ^ y for x, y in zip(key, ipad)]) # Step 2
    key_opad = bytes([x ^ y for x, y in zip(key, opad)]) # Step 5

    # Perform the HMAC calculation using XOR
    inner_hash = xor_hash(key_ipad + message, block_size) # Step 3 and 4
    hmac_result = xor_hash(key_opad + inner_hash, block_size) # Step 6 and 7
    
    return hmac_result

def hmac_with_sha256(key, message):
    """
    Custom HMAC implementation using SHA-256 as the hash function.

    Parameters:
    key (int): The secret key for HMAC, derived from Diffie-Hellman.
    message (str): The message to be authenticated.

    Returns:
    bytes: The generated HMAC tag en hex.
    """
    block_size = 64  # Block size for SHA-256

    # Convert the shared key integer and message to bytes
    shared_key = str(key).encode()
    message = str(message).encode()

    # Step 1
    # If the key is longer than the block size, hash it using SHA-256
    if len(shared_key) > block_size:
        shared_key = hashlib.sha256(shared_key).digest()

    # If the key is shorter than the block size, pad with zeros
    if len(shared_key) < block_size:
        shared_key = shared_key + b'\x00' * (block_size - len(shared_key))

    # Define inner and outer padding
    ipad = bytes([0x36] * block_size)
    opad = bytes([0x5c] * block_size)

    # XOR the key with ipad and opad
    key_ipad = bytes([x ^ y for x, y in zip(shared_key, ipad)]) # Step 2
    key_opad = bytes([x ^ y for x, y in zip(shared_key, opad)]) # Step 5

    # Perform the HMAC calculation using SHA-256
    inner_hash = hashlib.sha256(key_ipad + message).digest()  # Step 3 and 4
    hmac_result = hashlib.sha256(key_opad + inner_hash).hexdigest()  # Step 6 and 7
    
    return hmac_result

if __name__ == "__main__":

    p = 23456
    g = 5
    key = diffie_hellman(p, g)

    # *** TESTING WITH XOR-BASED HMAC ***
    print("\n--- Testing HMAC with XOR Hash ---")

    # Alice send a message to Bob and she will compute the HMAC of the message using the shared key
    message = 'Hello, Bob!'
    hmac_tag = hmac_xor_hash(key, message)  # Using the XOR-based HMAC function
    print('Alice sends the next message:', message)
    #print('Shared secret key:',key)

    # Alice sends both the message and the HMAC tag to Bob
    # Bob receives the message along with the HMAC tag sent by Alice.
    received_message = message
    received_hmac_tag = hmac_tag

    # Bob uses the same shared secret key to compute the HMAC of the received message:
    computed_hmac = hmac_xor_hash(key, received_message) 

    if computed_hmac == received_hmac_tag:
        print('HMAC tag with XOR hash:', computed_hmac.hex())
        print("Message authenticated and verified as untampered.")
    else:
        print("Message authentication failed.")

    print('--------------------------------------------------------------')

    # *** TESTING WITH SHA-256-BASED HMAC ***
    print("\n--- Testing HMAC with SHA-256 Hash ---")

    # Bob send a message to Alice and he will compute the HMAC of the message using the shared key
    message = 'Bye, Alice!'
    hmac_tag = hmac_with_sha256(key, message)  # Using the Sha-256 hash in HMAC function
    print('Bob sends the next message:', message)
    #print('Shared secret key:',key)

    # Bob sends both the message and the HMAC tag
    # Alice receives the message along with the HMAC tag sent.
    received_message = message
    received_hmac_tag = hmac_tag

    # Alice uses the same shared secret key to compute the HMAC of the received message:
    computed_hmac = hmac_with_sha256(key, received_message) 

    if computed_hmac == received_hmac_tag:
        print('HMAC tag with SHA-256 hash: ', computed_hmac)
        print("Message authenticated and verified as untampered.")
    else:
        print("Message authentication failed.")

