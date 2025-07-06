import sys

def matrix_chain_order(p):
    n = len(p) - 1
    m = [[0] * (n - i) for i in range(n)]  
    s = [[0] * (n - i - 1) for i in range(n)]  
    
    for l in range(2, n + 1):  # l is chain length
        for i in range(n - l + 1):
            j = i + l - 1
            m[i][j - i] = sys.maxsize  
            for k in range(i, j):
                q = m[i][k - i] + m[k + 1][j - (k + 1)] + p[i] * p[k + 1] * p[j + 1]
                if q < m[i][j - i]:
                    m[i][j - i] = q
                    s[i][j - i - 1] = k + 1  
    
    return m, s

def print_optimal_parens(s, i, j):
    if i == j:
        print(f"A{i+1}", end="")
    else:
        print("(", end="")
        print_optimal_parens(s, i, s[i][j - i - 1] - 1)  
        print_optimal_parens(s, s[i][j - i - 1], j)  
        print(")", end="")

def print_matrices(m, s):
    print("Matrix M (minimum costs):")
    print(" ".join(f"\t j={j+1}" for j in range(len(m[0]))))
    for i, row in enumerate(m):
        print(f"i={i+1}: ", "  ".join(f"{val:6}" for val in row))
    print("\nMatrix S (split points):")
    print(" ".join(f"\t j={j+2}" for j in range(len(s[0]))))
    for i, row in enumerate(s):
        print(f"i={i+1}: ", "  ".join(f"{val:6}" for val in row))

# Example usage

p = [10,20,30,40,50,60]
m, s = matrix_chain_order(p)
print("Minimum number of scalar multiplications: ", m[0][-1])
print("Optimal parenthesization: ", end="")
print_optimal_parens(s, 0, len(p) - 2)
print("\n")
print_matrices(m, s)
