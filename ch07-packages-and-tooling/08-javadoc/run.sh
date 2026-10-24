#!/usr/bin/env bash
# Run the demo, then turn the /** ... */ comments into a website in sandbox/docs/
set -e
cd "$(dirname "$0")"
java Thermostat.java
rm -rf sandbox/docs
javadoc -quiet -d sandbox/docs Thermostat.java > /dev/null
echo "Docs generated: sandbox/docs/index.html exists? $( [ -f sandbox/docs/index.html ] && echo yes || echo no )"
