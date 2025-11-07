#!/bin/bash
echo "=== Testing Multiple Dice Rolling Simulator ==="

echo -e "\nTest 1: Basic functionality"
kotlin DiceKt

echo -e "\nTest 2: User interaction with custom values"
echo -e "3\n8" | kotlin DiceKt

echo -e "\nTest 3: User interaction with defaults"
echo -e "\n" | kotlin DiceKt

echo -e "\nTest 4: Direct function calls"
kotlinc Dice.kt -include-runtime -d dice.jar
java -jar dice.jar <<'INPUT'

// Test various calling styles
println("=== Direct Function Call Tests ===")
println("1. rollDice()")
rollDice()

println("2. rollDice(3)")
rollDice(3)

println("3. rollDice(sides = 20)")
rollDice(sides = 20)

println("4. rollDice(numDice = 2, sides = 8)")
rollDice(numDice = 2, sides = 8)

println("5. rollDice(sides = 12, numDice = 4)")
rollDice(sides = 12, numDice = 4)
INPUT
