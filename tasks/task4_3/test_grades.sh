#!/bin/bash
echo "=== Testing Module Grade Calculator ==="

echo -e "\nTest 1: Valid marks - Distinction"
kotlin ModuleGradeKt 85 90 78

echo -e "\nTest 2: Valid marks - Pass"
kotlin ModuleGradeKt 65 55 60

echo -e "\nTest 3: Valid marks - Fail"
kotlin ModuleGradeKt 30 35 25

echo -e "\nTest 4: Boundary cases"
kotlin ModuleGradeKt 70 69 40

echo -e "\nTest 5: Too few arguments"
kotlin ModuleGradeKt 85 90

echo -e "\nTest 6: Too many arguments"
kotlin ModuleGradeKt 85 90 78 82

echo -e "\nTest 7: Non-integer arguments"
kotlin ModuleGradeKt 85 ninety 78

echo -e "\nTest 8: Marks out of range"
kotlin ModuleGradeKt 85 105 78

echo -e "\nTest 9: Negative marks"
kotlin ModuleGradeKt 85 -5 78

echo -e "\nTest 10: Perfect scores"
kotlin ModuleGradeKt 100 100 100

echo -e "\nTest 11: Zero scores"
kotlin ModuleGradeKt 0 0 0
