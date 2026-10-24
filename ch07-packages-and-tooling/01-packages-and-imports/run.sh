#!/usr/bin/env bash
# Compile every .java file under src into out/, keeping the package folders, then run the main class.
set -e
cd "$(dirname "$0")"
rm -rf out
javac -d out $(find src -name "*.java")
java -cp out com.cafe.app.Main
