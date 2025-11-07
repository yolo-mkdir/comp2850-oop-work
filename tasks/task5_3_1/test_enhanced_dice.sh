#!/bin/bash
echo "=== Testing Enhanced Dice Rolling Simulator ==="

echo -e "\nTest 1: Basic demonstration"
kotlin DieKt

echo -e "\nTest 2: User chooses default (yes)"
echo "y" | kotlin DieKt

echo -e "\nTest 3: User chooses custom (no, then 20)"
echo -e "n\n20" | kotlin DieKt

echo -e "\nTest 4: Direct function calls with different parameters"
kotlin -cp . DieKt <<'INPUT'
println("Direct rollDie(): ${rollDie()}")
println("rollDie(10): ${rollDie(10)}")
println("rollDie(sides = 100): ${rollDie(sides = 100)}")
INPUT
