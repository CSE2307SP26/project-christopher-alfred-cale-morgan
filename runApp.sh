#!/bin/bash

#put commands here to compile and run your app from command line

# Stop on error
set -e

echo "Compiling..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin src/main/*.java

echo "Running app..."

# Run your main class (change Main if needed)
java -cp bin main.MainMenu