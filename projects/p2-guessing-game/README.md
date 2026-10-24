# Project 2: Number Guessing Game

Pick a difficulty, guess the secret number with hot/cold hints, play as many rounds as you like, and see your statistics at the end.

```
p2-guessing-game/
├── src/GuessingGame.java   game loop, difficulty switch, hints, stats
├── sample-input.txt        scripted guesses for seed 42
├── run.sh                  bash run.sh  [seed]
└── test.sh                 plays two known rounds and checks the result
```

Run: `bash run.sh` (random) or `bash run.sh 42` (repeatable). Test: `bash test.sh`.

**Uses (Chapter 2):** `switch` expressions, `while`, `do-while`, `for`, nested loops, `break`, `continue`, comparison and logical operators.

**Try next:** save the best score to a file (Chapter 6) or add a two-player mode.
