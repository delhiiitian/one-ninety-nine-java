#!/usr/bin/env bash
# Feeds sample answers and checks the key numbers on the receipt.
set -e
cd "$(dirname "$0")"
out=$(java src/BillSplitter.java < sample-input.txt 2>/dev/null)
echo "$out" | grep -q "Subtotal                     21.50"
echo "$out" | grep -q "TOTAL                        27.30"
echo "$out" | grep -q "Extra tip from rounding       0.02"
echo "$out" | grep -q "Each of 4 pays                6.83"
echo "p1-bill-splitter: OK"
