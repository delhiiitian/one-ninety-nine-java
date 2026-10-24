#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf out dice.jar

javac -d out src/com/dice/DiceRoller.java

# c = create, f = file name, e = entry point (writes Main-Class into the manifest)
jar cfe dice.jar com.dice.DiceRoller -C out .

echo "Contents of dice.jar:"
jar tf dice.jar

# Anyone with Java can now run the single file
java -jar dice.jar
java -jar dice.jar 5
