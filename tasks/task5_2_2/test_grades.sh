#!/bin/bash
echo "=== Testing Exam Grades Calculator ==="

echo -e "\nTest 1: Various marks"
kotlin GradesKt 85 92 78 45 32 67 55 88 29 71

echo -e "\nTest 2: Boundary marks"
kotlin GradesKt 0 39 40 69 70 100

echo -e "\nTest 3: Single distinction"
kotlin GradesKt 75

echo -e "\nTest 4: Single pass"
kotlin GradesKt 50

echo -e "\nTest 5: Single fail"
kotlin GradesKt 35

echo -e "\nTest 6: Invalid marks (text)"
kotlin GradesKt 85 ninety 78

echo -e "\nTest 7: Invalid marks (out of range)"
kotlin GradesKt 85 105 78 -5

echo -e "\nTest 8: No arguments"
kotlin GradesKt

echo -e "\nTest 9: Mixed valid and invalid"
kotlin GradesKt 85 abc 78 105 60 -10 45
