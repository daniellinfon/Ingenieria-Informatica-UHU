import time
import matplotlib.pyplot as plt

def greedy_coin_change(coins, total):
    coins.sort(reverse=True)  # Sort coins in descending order
    coin_count = {}
    
    for coin in coins:
        if total >= coin:
            count = total // coin
            total -= count * coin
            coin_count[coin] = count

    return coin_count

def dynamic_coin_change(coins, total):
    dp = [float('inf')] * (total + 1)
    dp[0] = 0  # No coins needed for 0

    # Track which coin was last used to reach each amount
    last_used_coin = [-1] * (total + 1)

    for i in range(1, total + 1):
        for coin in coins:
            if coin <= i and dp[i - coin] + 1 < dp[i]:
                dp[i] = dp[i - coin] + 1
                last_used_coin[i] = coin

    # Reconstruct the coins used
    if dp[total] == float('inf'):
        return -1, []  # No solution found

    coins_used = []
    current = total
    while current > 0:
        coin = last_used_coin[current]
        coins_used.append(coin)
        current -= coin

    return dp[total], coins_used

# Benchmarking execution times
def benchmark_algorithms():
    coin_denominations = [1, 5, 10, 20]  # Norwegian coin system
    N_values = list(range(1, 101))  # Total amounts from 1 to 100
    greedy_times = []
    dp_times = []

    for N in N_values:
        # Measure Greedy Algorithm time
        start_time = time.perf_counter()
        greedy_coin_change(coin_denominations, N)
        end_time = time.perf_counter()
        greedy_times.append(end_time - start_time)

        # Measure Dynamic Programming Algorithm time
        start_time = time.perf_counter()
        dynamic_coin_change(coin_denominations, N)
        end_time = time.perf_counter()
        dp_times.append(end_time - start_time)

    return N_values, greedy_times, dp_times

# Generate and plot the graph
def plot_execution_times(N_values, greedy_times, dp_times):
    plt.figure(figsize=(10, 6))
    plt.plot(N_values, greedy_times, label='Greedy Algorithm (Measured Time)', color='green', linestyle='--')
    plt.plot(N_values, dp_times, label='Dynamic Programming (Measured Time)', color='blue')

    # Formatting the plot
    plt.title('Execution Time: Greedy vs. Dynamic Programming')
    plt.xlabel('Total Amount (N)')
    plt.ylabel('Execution Time (seconds)')
    plt.legend()
    plt.grid(True)
    plt.tight_layout()

    # Show plot
    plt.show()

# Run the benchmark and plot the graph
N_values, greedy_times, dp_times = benchmark_algorithms()
plot_execution_times(N_values, greedy_times, dp_times)
