# Project 4: Library Manager

A command-driven librarian's tool: list and search books, lend and return them, keep a waitlist per book, and see recent activity.

```
p4-library-manager/
├── src/
│   ├── LibraryApp.java   command loop (help, list, search, borrow, return, members, genres, recent, quit)
│   ├── Library.java      the rules, built on HashMap, HashSet, ArrayDeque, List and Comparator
│   ├── Book.java         record
│   └── Member.java       member and current loans (max 3)
├── sample-input.txt
├── run.sh
└── test.sh
```

Run: `bash run.sh` or `bash run.sh --sample`. Test: `bash test.sh`.

**Uses (Chapter 4):** `HashMap` for lookup, `HashSet` for "is it on loan?", `ArrayDeque` as both a queue (waitlist) and a stack (history), `ArrayList`, sorting with `Comparator`, records, `TreeMap`.
