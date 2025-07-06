# Toy Blockchain Project

This repository contains a simple implementation of a blockchain with functionalities for transactions, mining, wallets, and basic consensus mechanisms. The project also includes tests and tools for analyzing performance metrics like transaction throughput and block mining times.

## Directory Contents

- `block.py`: Defines the `Block` class, which includes block data structures, proof-of-work functions, and integration with Merkle trees for transaction verification.
- `blockchain.py`: Implements the `Blockchain` class, managing the chain, adding blocks, mining, and handling consensus.
- `transaction.py`: Contains the `Transaction` class for creating and managing individual transactions.
- `wallet.py`: Defines the `Wallet` class, which handles private/public key generation and signing transactions.
- `main.py`: Main script for testing blockchain functionaly.
- `main_async.py`: Another script for testing the mining speed and throughput.
- `README.md`: This file, providing an overview of the project, installation requirements, and usage instructions.

### External Packages

Please install the following packages before running the scripts:
- `ecdsa`: For elliptic curve digital signatures, which secure the transactions.
- `matplotlib`: For generating performance graphs.

Install the packages with the following command:

```bash
pip install ecdsa matplotlib
