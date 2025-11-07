#!/bin/bash
echo "=== Testing String Extension Function ==="

echo -e "\nTest 1: Short string"
echo "Hello" | kotlin StringFuncKt

echo -e "\nTest 2: Medium string"
echo "This is a medium string" | kotlin StringFuncKt

echo -e "\nTest 3: Long string"
echo "This string is definitely way too long for the limit" | kotlin StringFuncKt

echo -e "\nTest 4: Exactly 20 characters"
echo "12345678901234567890" | kotlin StringFuncKt

echo -e "\nTest 5: Exactly 21 characters"
echo "123456789012345678901" | kotlin StringFuncKt

echo -e "\nTest 6: Empty string"
echo "" | kotlin StringFuncKt

echo -e "\nTest 7: String with spaces and special chars"
echo "Hello @ World! #123" | kotlin StringFuncKt
