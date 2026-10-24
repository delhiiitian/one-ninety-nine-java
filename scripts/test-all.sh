#!/usr/bin/env bash
# Runs every chapter example and every project test.
#   bash scripts/test-all.sh            check everything
#   bash scripts/test-all.sh --update   re-record each example's expected-output.txt
# An example folder is run with run.sh if it has one, otherwise with "java File.java",
# feeding input.txt (keyboard answers) and args.txt (command-line arguments) when present.
set -u
cd "$(dirname "$0")/.."
export JAVA_TOOL_OPTIONS="${JAVA_TOOL_OPTIONS:-}"
update=false; [ "${1:-}" = "--update" ] && update=true
pass=0; fail=0; failed=()

run_example() {
  local dir=$1
  ( cd "$dir"
    if [ -f run.sh ]; then bash run.sh
    else
      local src; src=$(ls *.java | head -1)
      local args=""; [ -f args.txt ] && args=$(cat args.txt)
      if [ -f input.txt ]; then java "$src" $args < input.txt; else java "$src" $args < /dev/null; fi
    fi ) 2>&1 | grep -v '^Picked up JAVA_TOOL_OPTIONS'
  return "${PIPESTATUS[0]}"
}

for dir in ch*/*/; do
  dir=${dir%/}
  out=$(run_example "$dir"); code=$?
  if [ $code -ne 0 ]; then
    fail=$((fail+1)); failed+=("$dir (exit $code)")
  elif $update; then
    printf '%s\n' "$out" > "$dir/expected-output.txt"; pass=$((pass+1))
  elif [ -f "$dir/expected-output.txt" ] && [ "$out" != "$(cat "$dir/expected-output.txt")" ]; then
    fail=$((fail+1)); failed+=("$dir (output changed)")
    diff <(printf '%s\n' "$out") "$dir/expected-output.txt" | head -10
  else
    pass=$((pass+1))
  fi
done

for t in projects/*/test.sh; do
  if bash "$t" 2>&1 | grep -v '^Picked up' ; [ "${PIPESTATUS[0]}" -eq 0 ]; then pass=$((pass+1)); else fail=$((fail+1)); failed+=("$t"); fi
done

# Remove files the examples created
find ch* projects \( -name sandbox -o -name out -o -name target -o -name '*.jar' -o -name data \) -prune -exec rm -rf {} + 2>/dev/null

echo "----------------------------------------"
echo "Passed: $pass   Failed: $fail"
for f in "${failed[@]+"${failed[@]}"}"; do echo "  FAILED: $f"; done
[ $fail -eq 0 ]
