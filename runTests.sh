#!/bin/bash

set -e

echo "Compiling Bank App and Tests..."

mkdir -p bin
find src -name "*.java" > sources.txt

javac -d bin -cp "test-lib/junit-platform-console-standalone-1.13.0-M3.jar" @sources.txt

rm sources.txt

echo "Running tests..."

java -jar test-lib/junit-platform-console-standalone-1.13.0-M3.jar execute -cp bin --scan-class-path