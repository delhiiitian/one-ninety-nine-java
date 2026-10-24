#!/usr/bin/env bash
# Build without Maven: compile the main code, package a runnable JAR, analyse sample.txt.
set -e
cd "$(dirname "$0")"
rm -rf out && mkdir -p out
javac -d out $(find src/main/java -name "*.java")
jar cfe textkit-plain.jar com.textkit.cli.Main -C out .
java -jar textkit-plain.jar "${@:-sample.txt}"
