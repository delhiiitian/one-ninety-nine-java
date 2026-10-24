#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
out=$(bash run.sh --sample 2>/dev/null)
echo "$out" | grep -q "2 match(es)"
echo "$out" | grep -q "Bilal is #1 on the waitlist."
echo "$out" | grep -q "Chloe is #2 on the waitlist."
echo "$out" | grep -q "Aditi returned 'The Hobbit'. Bilal (from waitlist) borrowed 'The Hobbit'."
echo "$out" | grep -q "Fantasy: 2"
echo "$out" | grep -q "Unknown command 'dance'"
echo "p4-library-manager: OK"
