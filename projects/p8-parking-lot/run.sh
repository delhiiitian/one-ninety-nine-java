#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf out
javac -d out $(find src -name "*.java")
java -cp out com.parkinglot.app.Main
