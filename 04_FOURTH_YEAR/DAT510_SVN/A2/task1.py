import random

def diffie_hellman(p: int, g: int):

    # Step 1: Define public parameters (p and g)  
    # Step 2: Create private keys (random integers)

    a = random.randint(1, p-1)  # Alice's private key
    b = random.randint(1, p-1)  # Bob's private key

    # Step 3: Generate public keys

    A = pow(g, a, p)  # Alice's public key (g^a % p)
    B = pow(g, b, p)  # Bob's public key (g^b % p)

    # Step 4: Calculate the shared secret key

    S_Alice = pow(B, a, p)  # Alice calculates the shared secret
    S_Bob = pow(A, b, p)    # Bob calculates the shared secret

    assert S_Alice == S_Bob  # Verify that both shared secrets are the same

    print(f"Shared secret key: {S_Alice}")

    return S_Alice

if __name__ == "__main__":
    p = 23456
    g = 5

    print('Parameters')
    print('p =',p)
    print('g =', g)
    for i in range(1, 4):
        print(f"\n--- Test {i} ---")
        diffie_hellman(p, g)
        