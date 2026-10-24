#!/usr/bin/env bash
# Full build with Maven (compiles, runs the JUnit tests, packages target/textkit.jar), then a smoke test.
set -e
cd "$(dirname "$0")"
if command -v mvn >/dev/null; then
  mvn -q -B package
  jar=target/textkit.jar
else
  echo "(Maven not found: skipping JUnit tests, building with javac)"
  bash run.sh > /dev/null
  jar=textkit-plain.jar
fi
out=$(java -jar $jar --top 3 sample.txt 2>/dev/null)
echo "$out" | grep -q "Words: 95 (unique 73)"
echo "$out" | grep -q "Top words: java="
set +e; java -jar $jar missing.txt >/dev/null 2>&1; code=$?; set -e
[ "$code" = "2" ]
echo "p7-textkit: OK"
