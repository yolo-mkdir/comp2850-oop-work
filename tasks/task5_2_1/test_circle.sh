#!/bin/bash
echo "=== Testing Circle Calculator ==="

echo -e "\nTest 1: Standard circle (radius = 5)"
echo "5" | kotlin CircleKt

echo -e "\nTest 2: Unit circle (radius = 1)"
echo "1" | kotlin CircleKt

echo -e "\nTest 3: Large circle (radius = 100)"
echo "100" | kotlin CircleKt

echo -e "\nTest 4: Decimal radius (radius = 2.5)"
echo "2.5" | kotlin CircleKt

echo -e "\nTest 5: Invalid input (text)"
echo "abc" | kotlin CircleKt

echo -e "\nTest 6: Zero radius"
echo "0" | kotlin CircleKt

echo -e "\nTest 7: Negative radius (should error)"
echo "-5" | kotlin CircleKt

echo -e "\nTest 8: Very small circle (radius = 0.001)"
echo "0.001" | kotlin CircleKt
