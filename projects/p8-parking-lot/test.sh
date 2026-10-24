#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
out=$(bash run.sh 2>/dev/null)
echo "$out" | grep -q "ElectricCar(KA03EV3333)  -> spot 0-M4"
echo "$out" | grep -q "Truck(KA05TR5555)        -> FULL"
echo "$out" | grep -q "Car, 3 hours of shopping       pays \$ 9.00"
echo "$out" | grep -q "EV, all day (capped)           pays \$20.00"
echo "$out" | grep -q "revenue today: \$46.00"
echo "p8-parking-lot: OK"
