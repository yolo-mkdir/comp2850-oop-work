#!/bin/bash
echo "=== Testing String Extension Property ==="

echo -e "\nTest 1: Short string"
echo "Hello" | kotlin StringPropKt

echo -e "\nTest 2: Medium string"
echo "This is a medium string" | kotlin StringPropKt

echo -e "\nTest 3: Long string"
echo "This string is definitely way too long for the limit" | kotlin StringPropKt

echo -e "\nTest 4: Exactly 20 characters"
echo "12345678901234567890" | kotlin StringPropKt

echo -e "\nTest 5: Exactly 21 characters"
echo "123456789012345678901" | kotlin StringPropKt

echo -e "\nTest 6: Empty string"
echo "" | kotlin StringPropKt

echo -e "\nTest 7: Syntax demonstration"
kotlin StringPropKt <<'INPUT'

// Test the syntax difference
demonstrateSyntaxDifference()
INPUT
