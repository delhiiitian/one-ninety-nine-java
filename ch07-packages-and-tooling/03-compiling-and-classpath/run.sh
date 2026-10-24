#!/usr/bin/env bash
# Two separate code bases: a library and an app that uses it.
set -e
cd "$(dirname "$0")"
rm -rf library/out app/out

# Step 1: compile the library. javac turns .java (source) into .class (bytecode).
javac -d library/out library/src/textutils/Slug.java

# Step 2: compile the app. -cp (classpath) tells javac where to find the library's classes.
javac -cp library/out -d app/out app/src/BlogApp.java

# Step 3: run. The JVM needs BOTH folders on the classpath (':' separates entries; Windows uses ';').
java -cp app/out:library/out BlogApp
