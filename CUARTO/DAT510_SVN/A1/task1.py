def clean_text(text):
    """
    Cleans the text by removing non-alphabetic characters and converting it to uppercase.
    
    Parameter:
    - text: String of text to be cleaned.
    
    Returns:
    - Cleaned text containing only alphabetic characters in uppercase.
    """

    return ''.join(filter(str.isalpha, text)).upper()

def generate_transposition_key(phone_number):
    """
    Generates the transposition key based on the phone number.

    Parameter:
    - phone_number: String representing the phone number.

    Returns:
    - A string of rearranged numbers based on the digits of the phone number.
    """

    phone_number_list = list(phone_number)
    index = 1
    for i in range(0, 10):
        for pos,digit in enumerate(phone_number):
            digit = int(digit)
            if i == digit:
                phone_number_list[pos] = index
                index += 1
    
    phone_number_list =  [str(num) for num in phone_number_list]
    return ''.join(phone_number_list)
                

def transposition_cipher(text, key):
    """
    Performs a row transposition cipher based on the provided key.

    Parameter:
    - text: The text to be encrypted.
    - key: The transposition key used to rearrange the text.

    Returns:
    - The encrypted text after applying the transposition.
    """

    num_cols = len(key)
    columns = {}
    
    for i in range(1, num_cols + 1): # Every column
        words = ''
        for j in range(1, len(text)+1): # Every char
            if j % num_cols == i or (j % num_cols == 0 and i == num_cols):
                words += text[j-1]

        columns[i] = words

    columns_copy = {}
    ciphertext = ""

    for pos,digit in enumerate(key):
        digit = int(digit)
        columns_copy[digit] = columns[pos+1]

    for num in range(1,len(key)+1):
        ciphertext += columns_copy[num]

    return ciphertext

def caesar_cipher(text, shift):
    """
    Applies Caesar cipher to the text with a specified shift.
    
    Parameter:
    - text: The text to be encrypted.
    - shift: The number of positions to shift in the alphabet.
    
    Returns:
    - The encrypted text using Caesar cipher.
    """
    encrypted_text = []

    for char in text:
        char = str(char)
        if char.isalpha():
            shift_base = ord('A') if char.isupper() else ord('a')
            encrypted_text.append(chr((ord(char) - shift_base + shift) % 26 + shift_base))
        else:
            encrypted_text.append(char)
       
    return ''.join(encrypted_text)

def get_caesar_key(phone_number):
    """
    Gets the last two or one digits of the phone number.
    
    Parameter:
    - phone_number: String representing the phone number.

    Returns:
    - The key for the Caesar cipher.
    """
    key = int(phone_number[-2:]) if int(phone_number[-2:]) < 25 else int(phone_number[-1])
        
    return key


if __name__ == "__main__":

    # Request data
    print("\n*** Task 1: Apply Encryption ***")
    plaintext = input("Introduce the plain text: ")
    phone_number = input("Introduce the last five digits of your phone number: ")
    transposition_key = generate_transposition_key(phone_number)
    caesar_key = get_caesar_key(phone_number)
    
    print(f"Plain text: {plaintext}")
    print(f"Caesar key: {caesar_key}")
    print(f"Transposition key: {transposition_key}")

    plaintext = clean_text(plaintext)
    print(f"After cleaning the text: {plaintext}")

    plaintext = caesar_cipher(plaintext, caesar_key)
    print(f"Caesar cipher text: {plaintext}")

    ciphertext = transposition_cipher(plaintext, transposition_key)
    print(f"Encrypted message: {ciphertext}")