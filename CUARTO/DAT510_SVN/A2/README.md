# README for Cryptographic Secure Communication Project

## Project Overview

This project demonstrates the implementation of secure communication mechanisms using cryptographic protocols. It includes implementations of **Diffie-Hellman key exchange**, **HMAC (with XOR and SHA-256)**, and the **Single and Double Ratchet** mechanisms, which are fundamental for ensuring **forward secrecy** and **post-compromise security** in secure communications. These protocols are commonly used in modern encrypted messaging systems.

## Directory Contents

1. **task1.py**: Implements the **Diffie-Hellman key exchange** to derive a shared secret between two parties (Alice and Bob). The shared secret can be used for encryption and authentication purposes.
2. **task2.py**: Contains the implementations of two **HMAC** algorithms:
   - **HMAC with XOR**: A basic XOR-based HMAC implementation.
   - **HMAC with SHA-256**: A standard, secure HMAC implementation using the SHA-256 hash function.
3. **task3.py**: Implements encryption and decryption using **AES in CTR mode** and demonstrates the use of HMAC for message authentication. Alice sends a message to Bob, computes the HMAC, and encrypts the message.
4. **task4.py**: Implements the **Single Ratchet** mechanism. This mechanism updates the chain key after each message to ensure forward secrecy. Alice and Bob exchange messages using the Single Ratchet to ensure each message uses a unique key.
5. **task5.py**: Implements the **Double Ratchet** mechanism, combining the symmetric ratchet (from task4) with **periodic Diffie-Hellman key exchanges**. This ensures that even if one key is compromised, future communications remain secure.

## Requirements

This project requires Python 3 and the following packages:
- `cryptography`: For encryption and decryption.
- `hashlib`: For HMAC implementations.

To install the required packages, run:

```bash
pip install cryptography
