#!/usr/bin/env bash
# Usage: bash run.sh add 2026-10-03 food 12.50 Lunch    |  bash run.sh summary 2026-10
set -e
cd "$(dirname "$0")"
mkdir -p out
javac -d out src/*.java
java -cp out ExpenseTracker "$@"
