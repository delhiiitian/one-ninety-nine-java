#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
out=$(bash run.sh --sample 2>/dev/null)
echo "$out" | grep -q "DECLINED: Account AC1002 is short by \$380.00. Try \$380.00 less."
echo "$out" | grep -q "ERROR: amounts must be numbers"
echo "$out" | grep -q "ERROR: Amount must be positive"
echo "$out" | grep -q "DECLINED: No account with id AC9999"
echo "$out" | grep -q "ERROR: missing details for 'withdraw'"
echo "$out" | grep -q "is frozen after too many failed PIN attempts"
echo "$out" | grep -q "Total money in bank: \$870.50"
echo "p5-bank-system: OK"
