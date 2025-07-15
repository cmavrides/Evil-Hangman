# Evil Hangman

## Overview
Evil Hangman is a twist on the classic Hangman word-guessing game. Unlike traditional Hangman where the computer selects a word at the beginning and sticks with it, Evil Hangman cheats by changing the target word during gameplay to make it harder for the player to win.

## Authors
- Alexandros Patsalides
- Christodoulos Mavrides

## How to Run
To run the game, use the following command:

```
java team9.hw5.PlayHangman <dictionary_file> <word_length> <number_of_guesses>
```

Where:
- `<dictionary_file>`: Path to the dictionary file (e.g., dictionary.txt or Dictionary2.txt)
- `<word_length>`: Length of the word to guess
- `<number_of_guesses>`: Maximum number of incorrect guesses allowed

Example:
```
java team9.hw5.PlayHangman dictionary.txt 5 8
```
This will start a game with 5-letter words and 8 allowed guesses.

## How to Play
1. The game will display a series of underscores representing the letters of the hidden word.
2. Enter a letter when prompted.
3. The game will tell you if your guess was correct or incorrect.
4. Continue guessing letters until you either:
   - Correctly guess the word (win)
   - Run out of guesses (lose)

## Game Rules
- You can only guess one letter at a time.
- Duplicate guesses are not counted against you.
- The game will display your remaining guesses, the current state of the word, and your previous guesses.

## How Evil Hangman Works
Unlike traditional Hangman, Evil Hangman doesn't commit to a word at the beginning of the game. Instead:

1. It maintains a list of all words of the specified length from the dictionary.
2. When you guess a letter, it groups the remaining words based on where that letter appears.
3. It then selects the largest group of words to continue the game, making it as difficult as possible for you to win.
4. Only when the game ends does it finally commit to a specific word.

## Project Structure
- `Hangman.java`: Contains the core game logic and the "evil" algorithm
- `PlayHangman.java`: Contains the main method and handles user interaction
- `dictionary.txt`: A large dictionary with over 127,000 English words
- `Dictionary2.txt`: A small dictionary with 9 four-letter words (for testing/demonstration)

## Version
Version 4.0
