#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
if ! command -v mvn >/dev/null; then
  echo "Maven is not installed; see https://maven.apache.org/install.html"; exit 0
fi
# Compile, then copy the H2 driver JAR into target/dependency so plain 'java' can find it
mvn -q -B compile dependency:copy-dependencies
java -cp "target/classes:target/dependency/*" com.example.db.JdbcDemo
