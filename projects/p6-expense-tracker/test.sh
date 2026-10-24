#!/usr/bin/env bash
# Runs a full session against a throwaway data file.
set -e
cd "$(dirname "$0")"
rm -rf sandbox && mkdir -p sandbox
export EXPENSES_FILE=sandbox/expenses.csv
t() { bash run.sh "$@" 2>/dev/null; }
t add 2026-10-01 rent 250 October rent > /dev/null
t add 2026-10-03 food 12.50 Lunch, with Sam > /dev/null
t add 2026-10-09 transport 30 Metro card > /dev/null
t add 2026-10-15 food 140.25 Groceries > /dev/null
t add 2026-11-02 food 20 Pizza > /dev/null
echo "not,a,valid,line" >> sandbox/expenses.csv
out=$(t summary 2026-10)
echo "$out" | grep -q "Food         152.75"
echo "$out" | grep -q "TOTAL        432.75"
echo "$out" | grep -q "OVER BUDGET by 32.75"
echo "$out" | grep -q "Warning: line 7 skipped"
t list | grep -q "Lunch; with Sam"
t delete 3 | grep -q "Deleted: 2026-10-09,Transport,30.00,Metro card"
[ -f sandbox/expenses.csv.bak ]
t export 2026-10 | grep -q "reports/report-2026-10.txt"
grep -q "OVER BUDGET by 2.75" sandbox/reports/report-2026-10.txt
(t add 03-10-2026 food 1 x || true) | grep -q "Dates look like"
echo "p6-expense-tracker: OK"
