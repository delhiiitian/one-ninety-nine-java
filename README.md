# Java 199

Code for **Java 199**, a beginner's book that teaches Java in 199 pages: 9 chapters, 81 short runnable examples and 9 complete projects. Every example in the book links to a file in this repository.

## Get started

1. Install a JDK, version 21 or newer ([Adoptium Temurin](https://adoptium.net) is free).
2. Clone this repository: `git clone https://github.com/delhiiitian/one-ninety-nine-java.git`
3. Run any example from its folder:

```
cd ch01-basics/01-hello-world
java HelloWorld.java
```

Since Java 11 a single-file program can be run directly with `java File.java`, with no separate compile step. Multi-file examples and projects have a `run.sh`.

## Contents

| Chapter | Topic | Project |
|---|---|---|
| 1 | [Java Basics](ch01-basics/) | [Restaurant Bill Splitter](projects/p1-bill-splitter/) |
| 2 | [Control Flow](ch02-control-flow/) | [Number Guessing Game](projects/p2-guessing-game/) |
| 3 | [Methods and Your First Classes](ch03-methods/) | [Student Report Card](projects/p3-report-card/) |
| 4 | [Data Structures](ch04-data-structures/) | [Library Manager](projects/p4-library-manager/) |
| 5 | [Error Handling](ch05-error-handling/) | [Bank Account System](projects/p5-bank-system/) |
| 6 | [Files and I/O](ch06-files-and-io/) | [Expense Tracker](projects/p6-expense-tracker/) |
| 7 | [Packages, Modules and Tooling](ch07-packages-and-tooling/) | [TextKit](projects/p7-textkit/) |
| 8 | [Java's Superpowers: OOP, Generics, Lambdas, Streams](ch08-java-strengths/) | [Parking Lot System](projects/p8-parking-lot/) |
| 9 | [Java in the Real World](ch09-real-world/) | [URL Shortener Web Service](projects/p9-url-shortener/) |

## Layout

```
chNN-topic/NN-subtopic/   one folder per book subtopic: the program, expected-output.txt, and any input files
projects/pN-name/         one folder per chapter project: src/, README.md, run.sh, test.sh
scripts/test-all.sh       runs every example and every project test (used by CI)
```

## Test everything

```
bash scripts/test-all.sh
```

Examples in 7.5, 9.9 and Project 7 use Maven, which downloads JUnit and the H2 database driver the first time. Everything else needs only the JDK.
