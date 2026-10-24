# Project 1: Restaurant Bill Splitter

Asks for a three-course order, the tip and the number of diners, then prints a formatted receipt and what each person pays. Every share is rounded **up** to the cent so the restaurant is never short.

```
p1-bill-splitter/
├── src/BillSplitter.java   the whole program: input -> maths -> receipt
├── sample-input.txt        answers for a scripted run
├── run.sh                  bash run.sh            (type answers)
└── test.sh                 bash test.sh           (checks the receipt numbers)
```

Run: `bash run.sh` or `bash run.sh --sample`. Needs only a JDK (17+).

**Uses (Chapter 1):** variables, `int`/`long`/`double`, strings and `repeat`, arithmetic and integer division, casting, `Scanner`, `final` constants, `printf`.

**Try next:** ask for any number of dishes (Chapter 2 loops), or let each diner pay for what they ordered (Chapter 4 maps).
