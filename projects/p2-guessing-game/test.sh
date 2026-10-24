#!/usr/bin/env bash
# Seed 42 makes the secrets 11 (round 1, Easy) and 64 (round 2, Hard), so the answers are known.
set -e
cd "$(dirname "$0")"
out=$(java src/GuessingGame.java 42 < sample-input.txt 2>/dev/null)
echo "$out" | grep -q "Out of range! That one is free."
echo "$out" | grep -q "Correct! 11 found in 3 tries."
echo "$out" | grep -q "Out of tries. The number was 64."
echo "$out" | grep -q "Win rate:     50%"
echo "p2-guessing-game: OK"
