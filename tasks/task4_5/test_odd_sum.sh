#!/bin/bash
echo "=== Testing Odd Number Sum Calculator ==="

echo -e "\nTest 1: Small limit (5)"
echo "5" | kotlin OddSumKt

echo -e "\nTest 2: Medium limit (10)"
echo "10" | kotlin OddSumKt

echo -e "\nTest 3: Larger limit (100)"
echo "100" | kotlin OddSumKt

echo -e "\nTest 4: Very large limit (1000000)"
echo "1000000" | kotlin OddSumKt

echo -e "\nTest 5: Invalid input (text)"
echo "abc" | kotlin OddSumKt

echo -e "\nTest 6: Invalid input (negative)"
echo "-5" | kotlin OddSumKt

echo -e "\nTest 7: Boundary case (1)"
echo "1" | kotlin OddSumKt

echo -e "\nTest 8: Even limit (20)"
echo "20" | kotlin OddSumKt
