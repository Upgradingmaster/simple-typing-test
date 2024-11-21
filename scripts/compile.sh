#!/bin/bash

# Reads all source file names to memory
JAVA_FILES=$(find src -name "*.java")

# Compile all files in JAVA_FILES
# javac -d target -cp "./lib/json-20240303.jar:./lib/junit-platform-console-standalone-1.10.2.jar" @scripts/compile_data.txt
javac -d target -cp "./lib/json-20240303.jar:./lib/junit-platform-console-standalone-1.10.2.jar" $JAVA_FILES
