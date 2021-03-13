# Error Correcting Encoder-Decoder
Errors are inevitable both in life and in the digital world. Errors occur here and there and everywhere, and in this project you will not only imitate this process, but also process errors. It is a chance to experience what early developers had to cope with at the dawn of the computer era. Low-level programming is fun and insightful: try it and you’ll see.

## Table of Contents
* [About this program](#about-this-program)
* [Technologies](#technologies)
* [Program Description](#program-description)
* [Examples](#example)

## About this program
This project is a solution to the problem of JetBrains Academy - **"Error Correcting Encoder-Decoder"**.

This program reads the text the user wants to send, and simulates the sending through a poor internet connection making one-bit errors in every byte of the text. It uses **Hamming code [7,4]** to encode and decode data (you can read more about it on [this Wikipedia page](https://en.wikipedia.org/wiki/Hamming(7,4))).

## Technologies
- JDK 8

## Program Description
The program works in 3 modes: **encode**, **send** and **decode**.

If the mode is **encode** then the program takes the text from `send.txt`, converts it to ready-to-send form, and saves the resulted bytes into the file named `encoded.txt`.

If the mode is **send**, the program takes the file from `encoded.txt` and simulates the errors in its bytes (1 bit per byte), and saves the resulted bytes into the file named `received.txt`.

If the mode is **decode**, the program takes the file from `received.txt` and decodes it to the normal text, and after saves the text into the file named `decoded.txt`.

## Examples
The greater-than symbol followed by a space ( **>** ) represents the user input. Note that it's not part of the input.

**Example 1:**

```
Write a mode: > encode

send.txt:
text view: Test
hex view: 54 65 73 74
bin view: 01010100 01100101 01110011 01110100

encoded.txt:
expand: ..0.101. ..0.100. ..0.110. ..0.101. ..0.111. ..0.011. ..0.111. ..0.100.
parity: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000
hex view: 4A 98 CC 4A 1E 86 1E 98
```

**Example 2:**

```
Write a mode: > send

encoded.txt:
hex view: 4A 98 CC 4A 1E 86 1E 98
bin view: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000

received.txt:
bin view: 01011010 10001000 10001100 01001110 00010110 10100110 00111110 10010000
hex view: 5A 88 8C 4E 16 A6 3E 90
```

**Example 3:**

```
Write a mode: > decode

received.txt:
hex view: 5A 88 8C 4E 16 A6 3E 90
bin view: 01011010 10001000 10001100 01001110 00010110 10100110 00111110 10010000

decoded.txt:
correct: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000
decode: 01010100 01100101 01110011 01110100
hex view: 54 65 73 74
text view: Test
```
