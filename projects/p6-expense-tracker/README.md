# Project 6: Expense Tracker

A command-line tool that stores expenses in a CSV file, so your data survives between runs. Summaries by category, budget warnings, safe deletes with a backup, and text reports.

```
p6-expense-tracker/
├── src/
│   ├── ExpenseTracker.java  commands: add, list, summary, delete, export
│   ├── ExpenseStore.java    the only class that touches files (read, append, rewrite + .bak)
│   ├── Expense.java         record + CSV conversion
│   └── Report.java          summaries and the exported report file
├── config.properties        budget=400
├── run.sh
└── test.sh                  full session against a throwaway file
```

```
bash run.sh add 2026-10-03 food 12.50 Lunch with Sam
bash run.sh list 2026-10
bash run.sh summary 2026-10
bash run.sh delete 2
bash run.sh export 2026-10
```

Data lives in `data/expenses.csv`, or wherever `EXPENSES_FILE` points.

**Uses (Chapter 6):** `Path`/`Files`, buffered reading, appending, rewriting with a backup copy, CSV parsing that skips bad lines, `Properties`, folders.
