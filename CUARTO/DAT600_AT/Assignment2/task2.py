import heapq
import os

def fractional_knapsack(weights, values, capacity):
    # Value-to-weight ratio calculation
    n = len(weights)
    value_per_weight = [(values[i] / weights[i], i) for i in range(n)]
    value_per_weight.sort(reverse=True)
    
    # Selection of items
    total_value = 0
    fractions = [0] * n
    for vpw, i in value_per_weight:
        # The item fits completely
        if capacity >= weights[i]:
            capacity -= weights[i]
            total_value += values[i]
            fractions[i] = 1
        # Only a fraction of the item fits
        else:
            fraction = capacity / weights[i]
            total_value += values[i] * fraction
            fractions[i] = fraction
            break
    
    return total_value, fractions

def zero_one_knapsack(weights, values, capacity):
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    # Selection of items
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i-1][w], dp[i-1][w - weights[i-1]] + values[i-1])
            else:
                dp[i][w] = dp[i-1][w]
    
    # Traceback to find included items
    w = capacity
    selected_items = [0] * n
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i-1][w]:
            selected_items[i-1] = 1
            w -= weights[i-1]
    
    return dp[n][capacity], selected_items

def get_user_input():
    n_items = int(input("Enter the number of items: "))
    weights = []
    values = []
    os.system('cls' if os.name == 'nt' else 'clear')
    
    for i in range(n_items):
        weight = int(input(f"Enter the weight of item {i + 1}: "))
        value = int(input(f"Enter the value of item {i + 1}: "))
        os.system('cls' if os.name == 'nt' else 'clear')
        weights.append(weight)
        values.append(value)
    
    capacity = int(input("Enter the knapsack capacity: "))
    os.system('cls' if os.name == 'nt' else 'clear')
    
    return weights, values, capacity

# Get the data
weights, values, capacity = get_user_input()

print(f"Generated weights: {weights}")
print(f"Generated values: {values}")
print(f"Generated capacity: {capacity}\n")

# Solve fractional knapsack
fractional_value, fractions = fractional_knapsack(weights, values, capacity)
print(f"Fractional Knapsack Value: {fractional_value}")
print(f"Fractions of items taken: {fractions}\n")

# Solve 0-1 knapsack
zero_one_value, selected_items = zero_one_knapsack(weights, values, capacity)
print(f"0-1 Knapsack Value: {zero_one_value}")
print(f"Items selected (0 or 1): {selected_items}")
