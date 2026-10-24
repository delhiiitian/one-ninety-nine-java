#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf sandbox
export DATA_FILE=sandbox/links.csv
out=$(bash run.sh --demo 2>/dev/null)
echo "$out" | grep -q "POST valid URL   -> 201"
echo "$out" | grep -q "Same URL, same code? true"
echo "$out" | grep -q "POST bad URL     -> 400"
echo "$out" | grep -q "302 Location: https://docs.oracle.com/en/java/"
echo "$out" | grep -q "Clicks counted: 50"
echo "$out" | grep -q "GET unknown code -> 404"
[ "$(wc -l < sandbox/links.csv)" = "1" ]
# Restart: the link is loaded back from the CSV file and still resolves
out2=$(bash run.sh --demo 2>/dev/null)
echo "$out2" | grep -q "Same URL, same code? true"
[ "$(wc -l < sandbox/links.csv)" = "1" ]
echo "p9-url-shortener: OK"
