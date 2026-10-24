#!/usr/bin/env bash
# Compiles all classes in src/ into out/, then runs ReportCard. "--sample" feeds sample-input.txt.
set -e
cd "$(dirname "$0")"
rm -rf out
javac -d out src/*.java
if [ "$1" = "--sample" ]; then java -cp out ReportCard < sample-input.txt; else java -cp out ReportCard; fi
