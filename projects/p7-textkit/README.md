# Project 7: TextKit

A small text-analysis **library** (`com.textkit.core`) plus a **command-line tool** (`com.textkit.cli`) built on it: word and sentence counts, top words, reading time and a Flesch readability score. Built with Maven, tested with JUnit 5, shipped as a runnable JAR.

```
p7-textkit/
├── pom.xml                                  Maven build: JUnit dependency, runnable JAR
├── src/main/java/com/textkit/core/
│   ├── Tokenizer.java                       what counts as a word / sentence / syllable
│   ├── TextStats.java                       counts, top words, reading time
│   └── Readability.java                     Flesch score and label
├── src/main/java/com/textkit/cli/Main.java  argument parsing, logging, exit codes
├── src/test/java/com/textkit/core/          JUnit tests
├── sample.txt
├── run.sh                                   build with plain javac + jar (no Maven needed)
└── test.sh                                  mvn package (runs tests), then a smoke test
```

```
mvn package                         # compile, test, build target/textkit.jar
java -jar target/textkit.jar --top 5 sample.txt
java -jar target/textkit.jar --verbose sample.txt other.txt
```

**Uses (Chapter 7):** packages, `public`/package-private access, Maven, JUnit, `java.util.logging`, Javadoc, JAR packaging, exit codes.
