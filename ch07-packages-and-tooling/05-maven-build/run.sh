#!/usr/bin/env bash
# Maven compiles, runs the JUnit tests in src/test, and packages a JAR in target/.
set -e
cd "$(dirname "$0")"
if ! command -v mvn >/dev/null; then
  echo "Maven is not installed; see https://maven.apache.org/install.html"; exit 0
fi
mvn -q -B package
java -jar target/tip-calculator-1.0.0.jar
