#!/usr/bin/env bash
# Interactive run. Add "--sample" to feed the answers from sample-input.txt.
cd "$(dirname "$0")"
if [ "$1" = "--sample" ]; then java src/BillSplitter.java < sample-input.txt; else java src/BillSplitter.java; fi
