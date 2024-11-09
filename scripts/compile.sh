#!/bin/bash

# Writes all source code file names to compile_data.txt
find -name "*.java" > scripts/compile_data.txt

# Compile all files in compile_data.txt
javac -d target -cp "./lib/json-20240303.jar:./lib/junit-platform-console-standalone-1.10.2.jar" @scripts/compile_data.txt
