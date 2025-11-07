#!/bin/bash
echo "=== Testing Dice Rolling Simulator ==="

echo -e "\nTest 1: Standard 6-sided die"
echo "6" | kotlin DieKt

echo -e "\nTest 2: 20-sided die (common in RPG games)"
echo "20" | kotlin DieKt

echo -e "\nTest 3: 2-sided die (coin flip)"
echo "2" | kotlin DieKt

echo -e "\nTest 4: Invalid input (text)"
echo "abc" | kotlin DieKt

echo -e "\nTest 5: Invalid input (negative)"
echo "-5" | kotlin DieKt

echo -e "\nTest 1-sided die (always 1)"
echo "1" | kotlin DieKt

echo -e "\nTest 100-sided die"
echo "100" | kotlin DieKt
