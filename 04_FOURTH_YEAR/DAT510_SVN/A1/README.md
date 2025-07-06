## Assignment 1 Encryption Analysis and Optimization: Understanding Avalanche Effect and Enhancing Security with Block Ciphers

This repository contains all the scripts made to carry out the project.  Each task builds on the previous one.

## Content

### task1.py

This script mainly covers everything related to Caesar cipher and transposition cipher. It includes everything from obtaining the keys based on the phone number to the logic for encryption. 

When we run the script, it will request the plain text and a phone number and as a result it will display the encrypted text after applying Caesar Cipher followed by transposition

### task2.py

This script performs an avalanche effect analysis on an encryption system that combines the Caesar cipher and the transposition cipher. It first encrypts the original and modified text using these techniques. It then calculates the percentage of bitwise difference between the ciphertexts over multiple rounds to evaluate how small changes in the input affect the encrypted result. The script also measures the elapsed time in each round of analysis and displays the final results. The encryption parameters are obtained from the last digit of the provided phone number.

### task4.py

This script performs an avalanche effect analysis on an encryption system that uses a combination of Caesar, transposition, and Vigenère Autokey ciphers. It performs the same function as task2.py and this time displays a graph comparing the avalanche effect and the processing time per round.

This script performs an avalanche effect analysis on an encryption system that uses a combination of Caesar, transposition, and Vigenère Autokey ciphers. It performs the same function as task2.py and this time displays a graph comparing the avalanche effect and the processing time for each round.

For this, we have used the matplotlib package that is installed with the following command:

$pip install matplotlib

### task5.py

This script performs an avalanche effect analysis on an encryption system that uses a combination of Caesar, transposition, and AES ciphers in CTR (Counter) mode. It encrypts the original and modified text using these encryption methods. It also allows you to opt in to the Vigenère Autokey cipher. It again requests the data and evaluates the avalanche effect, plotting the results on a graph.

To program the CTR, we have used the cryptography package:

$pip install cryptography

