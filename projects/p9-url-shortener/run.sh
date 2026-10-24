#!/usr/bin/env bash
# bash run.sh          -> start the service on http://localhost:8080
# bash run.sh --demo   -> self-test: start, call the API as a client, stop
set -e
cd "$(dirname "$0")"
rm -rf out
javac -d out $(find src -name "*.java")
java -cp out com.shortlinks.Main "${1:---serve}"
