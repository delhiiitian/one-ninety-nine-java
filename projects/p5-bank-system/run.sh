#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf out
javac -d out src/*.java
if [ "$1" = "--sample" ]; then java -cp out BankApp < sample-input.txt; else java -cp out BankApp; fi
