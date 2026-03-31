#!/bin/bash

# Stop on error
set -e

echo "Compiling..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin src/main/*.java src/main/MenuOptions/*.java src/main/Utils/*.java

echo "Running app..."

java -cp bin main.MainMenu
