import os

def greedy_coin_change(coins, total):
    coins.sort(reverse=True)  # Sort coins in descending order
    coin_count = {}
    
    for coin in coins:
        if total >= coin:
            count = total // coin
            total -= count * coin
            coin_count[coin] = count

    return coin_count

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

result = greedy_coin_change(coins, total)
print("Coin usage:", result)
