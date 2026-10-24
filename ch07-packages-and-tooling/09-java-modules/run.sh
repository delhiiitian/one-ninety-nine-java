#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf out
# --module-source-path: one sub-folder per module under src/
javac -d out --module-source-path src $(find src -name "*.java")
# --module-path replaces the classpath; -m names module/mainClass
java --module-path out -m com.weather.app/com.weather.app.Main
