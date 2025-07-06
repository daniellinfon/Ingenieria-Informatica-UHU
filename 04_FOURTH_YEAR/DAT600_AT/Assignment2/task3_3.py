import os

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

def get_user_input():
    n_coins = int(input("Enter the number of coins: "))
    coins = []
    os.system('cls' if os.name == 'nt' else 'clear')
    
    for i in range(n_coins):
        coin = int(input(f"Enter the value of coin {i + 1}: "))
        os.system('cls' if os.name == 'nt' else 'clear')
        coins.append(coin)
    
    total = int(input("Enter the total value: "))
    os.system('cls' if os.name == 'nt' else 'clear')
    
    return coins, total

coins, total = get_user_input()

print(f"Generated coins: {coins}")
print(f"Generated total: {total}\n")

min_coins, coins_used = dynamic_coin_change(coins, total)
print(f"Minimum number of coins needed: {min_coins}")
print(f"Coins used: {coins_used}")
