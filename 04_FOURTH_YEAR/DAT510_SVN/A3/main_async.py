import time
import matplotlib.pyplot as plt
from blockchain import Blockchain
from wallet import Wallet
import numpy as np

# Function to generate a specific number of transactions
def generate_transactions(num_transactions):
    transactions = []
    for i in range(num_transactions):
        sender_id = f"User_{i % 10}"
        recipient_id = f"User_{(i + 1) % 10}"
        amount = i * 0.1
        sender_wallet = users[sender_id]
        recipient_wallet = users[recipient_id]

        transaction = sender_wallet.create_transaction(recipient_wallet.address, amount)
        transactions.append(transaction)

    return transactions

# Function to measure blockchain throughput with multiple trials
def test_throughput(max_tx_per_block_counts, num_trials=5):
    avg_tps_results = []
    std_tps_results = []

    print("\n=== TSP Test ===")

    transactions = generate_transactions(200)
    
    for max_tx in max_tx_per_block_counts:
        trial_tps = []

        for _ in range(num_trials):
            blockchain.max_transactions_per_block = max_tx

            # Add all transactions to the blockchain
            start_time = time.time()
            for transaction in transactions:
                blockchain.add_transaction(transaction)

            # Measure processing time for mining
            blockchain.mine()
            end_time = time.time()

            # Calculate TPS
            elapsed_time = end_time - start_time
            tps = 200 / elapsed_time if elapsed_time > 0 else 0
            trial_tps.append(tps)

            # Reset blockchain for the next iteration
            blockchain.chain = [blockchain.chain[0]]
            blockchain.unconfirmed_transactions = []

        # Calculate the average and standard deviation for the current max_tx
        avg_tps_results.append(np.mean(trial_tps))
        std_tps_results.append(np.std(trial_tps))

        print(f"\nMax transactions per block: {max_tx}")
        print(f"Average TPS: {avg_tps_results[-1]}")
        print(f"Standard deviation of TPS: {std_tps_results[-1]}")

    return avg_tps_results, std_tps_results

# Function to test mining times with different difficulties
def test_mining_times(difficulties, num_trials=5):
    avg_mining_times = []
    std_mining_times = []
    transactions = generate_transactions(100)

    print("\n=== Mining Speed Test ===")

    for difficulty in difficulties:
        trial_times = []

        for _ in range(num_trials):
            blockchain.difficulty = difficulty

            # Add all transactions to the blockchain
            for transaction in transactions:
                blockchain.add_transaction(transaction)

            # Measure mining time
            start_time = time.time()
            block_index = blockchain.mine()
            end_time = time.time()

            mining_time = end_time - start_time
            trial_times.append(mining_time)

            # Reset blockchain for the next iteration
            blockchain.chain = [blockchain.chain[0]]
            blockchain.unconfirmed_transactions = []

        # Calculate the average and standard deviation for the current difficulty
        avg_mining_times.append(np.mean(trial_times))
        std_mining_times.append(np.std(trial_times))

        print(f"\nDifficulty level: {difficulty}")
        print(f"Average mining time: {avg_mining_times[-1]} seconds")
        print(f"Standard deviation of mining time: {std_mining_times[-1]} seconds")

    return avg_mining_times, std_mining_times

# Blockchain and user initialization
max_tx_per_block = 10
difficulties = [1, 2, 3, 4, 5]  # Different difficulty levels to test
max_tx_per_block_counts = [5, 10, 20, 50, 100]  # Different transaction counts to test
blockchain = Blockchain(max_transactions_per_block=max_tx_per_block)

# Create wallets for users
users = {}
for i in range(10):
    user_id = f"User_{i}"
    users[user_id] = Wallet()

print("Experimental Evaluation"
      "\n1. Test Mining Speed."
      "\n2. Test Throughput.")
option = input("Choose an option: ")

if option == '1':
    avg_mining_times, std_mining_times = test_mining_times(difficulties)

    # Plot mining time results
    plt.errorbar(difficulties, avg_mining_times, yerr=std_mining_times, fmt='o', capsize=5)
    plt.title("Mining Time vs Difficulty")
    plt.xlabel("Difficulty")
    plt.ylabel("Mining Time (seconds)")
    plt.xticks(difficulties)
    plt.grid(True)
    plt.show()

elif option == '2':
    avg_tps_results, std_tps_results = test_throughput(max_tx_per_block_counts)

    # Plot TPS results with error bars
    plt.errorbar(max_tx_per_block_counts, avg_tps_results, yerr=std_tps_results, fmt='o', capsize=5)
    plt.title("Blockchain Throughput - TPS vs Max Transactions per Block")
    plt.xlabel("Max Transactions per Block")
    plt.ylabel("TPS (Transactions per Second)")
    plt.xticks(max_tx_per_block_counts)
    plt.grid(True)
    plt.show()

# Print the blockchain summary
print("\n=== Blockchain Summary ===")
print(blockchain)

# Check if the blockchain is valid
if blockchain.is_chain_valid():
    print("Blockchain is valid.")
else:
    print("Blockchain is NOT valid.")
