# Project 5: Bank Account System

An ATM-style console where every possible mistake (wrong PIN, not enough money, unknown account, typo in an amount, missing details) is caught and explained instead of crashing the program.

```
p5-bank-system/
├── src/
│   ├── BankApp.java                     command loop, the catch blocks live here
│   ├── Bank.java                        accounts + all-or-nothing transfers
│   ├── Account.java                     balance rules, PIN lockout, daily limit, statement
│   ├── BankException.java               base checked exception
│   ├── InsufficientFundsException.java  carries how much is missing
│   ├── AccountNotFoundException.java
│   └── AccountFrozenException.java
├── sample-input.txt
├── run.sh
└── test.sh
```

Run: `bash run.sh` or `bash run.sh --sample`. Test: `bash test.sh`.

**Uses (Chapter 5):** custom checked exceptions in a hierarchy, `throw`, multiple `catch` blocks ordered specific-to-general, `finally`, unchecked exceptions for programming errors.
