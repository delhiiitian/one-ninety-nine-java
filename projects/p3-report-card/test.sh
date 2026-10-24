#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
out=$(bash run.sh --sample 2>/dev/null)
echo "$out" | grep -q "Marks must be 0-100, try again."
echo "$out" | grep -q "Total 354 / 400   Average 88.5   Grade A"
echo "$out" | grep -q "Result: NEEDS SUPPORT"
echo "$out" | grep -q "Top student:   Meera Iyer (A)"
echo "$out" | grep -q "Highest in English: 95"
echo "p3-report-card: OK"
