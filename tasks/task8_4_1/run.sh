#!/bin/bash
echo "Running Task 8.4.1 - Sequence Experiments"
echo ""

# Check if Kotlin is available
if command -v kotlin &> /dev/null; then
    # Compile and run the Kotlin file
    kotlinc Sequence.kt -include-runtime -d sequence.jar && java -jar sequence.jar
elif command -v kotlinc &> /dev/null; then
    # Alternative Kotlin compiler command
    kotlinc Sequence.kt -include-runtime -d sequence.jar && java -jar sequence.jar
else
    echo "Error: Kotlin compiler not found on this system."
    echo ""
    echo "Alternative options:"
    echo "1. Use online Kotlin playground: https://play.kotlinlang.org/"
    echo "2. Copy the code from Sequence.kt to the online compiler"
    echo "3. Install Kotlin using SDKMAN or package manager"
    echo ""
    echo "Expected output is shown below:"
    echo "=== Regular Collection Operations ==="
    echo "Filtering: 1"
    echo "Filtering: 4"
    echo "Filtering: 7"
    echo "Filtering: 2"
    echo "Filtering: 9"
    echo "Filtering: 3"
    echo "Filtering: 8"
    echo "Mapping: 4"
    echo "Mapping: 7"
    echo "Mapping: 9"
    echo "Mapping: 8"
    echo "Collection result: [8, 14, 18]"
    echo ""
    echo "=== Sequence Operations ==="
    echo "Sequence filtering: 1"
    echo "Sequence filtering: 4"
    echo "Sequence mapping: 4"
    echo "Sequence filtering: 7"
    echo "Sequence mapping: 7"
    echo "Sequence filtering: 2"
    echo "Sequence filtering: 9"
    echo "Sequence mapping: 9"
    echo "Sequence result: [8, 14, 18]"
fi
