/**
 * Task 5.3.2 - Multiple Dice Rolling Simulator
 * 
 * This program simulates rolling multiple dice of the same type.
 */

fun main() {
    println("=== Multiple Dice Rolling Simulator ===")
    
    // Test 1: All defaults (1 die, 6 sides)
    println("\n1. Testing with ALL DEFAULTS:")
    println("   rollDice()")
    rollDice()
    
    // Test 2: Default sides (6), custom number of dice
    println("\n2. Testing with DEFAULT SIDES, custom dice count:")
    println("   rollDice(3) - 3 dice, 6 sides each")
    rollDice(3)
    
    // Test 3: Default dice count (1), custom sides
    println("\n3. Testing with DEFAULT DICE COUNT, custom sides:")
    println("   rollDice(20) - Wait, this would be ambiguous!")
    println("   Instead, using NAMED PARAMETER:")
    println("   rollDice(sides = 20) - 1 die, 20 sides")
    rollDice(sides = 20)
    
    // Test 4: Both parameters specified positionally
    println("\n4. Testing with BOTH PARAMETERS (positional):")
    println("   rollDice(5, 10) - 5 dice, 10 sides each")
    rollDice(5, 10)
    
    // Test 5: Both parameters specified with names
    println("\n5. Testing with BOTH PARAMETERS (named):")
    println("   rollDice(numDice = 2, sides = 8) - 2 dice, 8 sides each")
    rollDice(numDice = 2, sides = 8)
    
    // Test 6: Named parameters in different order
    println("\n6. Testing NAMED PARAMETERS in different order:")
    println("   rollDice(sides = 12, numDice = 4) - 4 dice, 12 sides each")
    rollDice(sides = 12, numDice = 4)
    
    // Test 7: Common gaming dice combinations
    println("\n7. Testing COMMON GAMING COMBINATIONS:")
    testCommonGamingDice()
    
    // Test 8: User interaction demonstration
    println("\n8. USER INTERACTION DEMONSTRATION:")
    userInteractionDemo()
}

/**
 * Simulates rolling multiple dice of the same type.
 * 
 * @param numDice The number of dice to roll (default = 1)
 * @param sides The number of sides on each die (default = 6)
 */
fun rollDice(numDice: Int = 1, sides: Int = 6) {
    // Validate parameters
    if (numDice <= 0) {
        println("   ERROR: Number of dice must be positive")
        return
    }
    if (sides <= 0) {
        println("   ERROR: Number of sides must be positive")
        return
    }
    
    // Roll the dice and calculate individual results
    val rolls = List(numDice) { (1..sides).random() }
    val total = rolls.sum()
    
    // Display the results
    println("   Rolling $numDice ${sides}-sided dice...")
    
    if (numDice == 1) {
        println("   Result: $total")
    } else {
        println("   Individual rolls: ${rolls.joinToString(" + ")}")
        println("   Total: $total")
    }
    
    // Additional information for multiple dice
    if (numDice > 1) {
        val average = total.toDouble() / numDice
        val minPossible = numDice
        val maxPossible = numDice * sides
        println("   Average per die: ${"%.2f".format(average)}")
        println("   Range: $minPossible - $maxPossible")
    }
    
    println("   " + "-".repeat(30))
}

/**
 * Tests common dice combinations used in gaming
 */
fun testCommonGamingDice() {
    val commonCombinations = listOf(
        "2d6" to Pair(2, 6),    // Common in many board games
        "3d6" to Pair(3, 6),    // Character stats in RPGs
        "1d20" to Pair(1, 20),  // D&D attack rolls
        "2d10" to Pair(2, 10),  // Percentile dice
        "4d4" to Pair(4, 4),    // Low damage rolls
        "1d100" to Pair(1, 100) // Percentile
    )
    
    for ((name, params) in commonCombinations) {
        val (numDice, sides) = params
        println("   $name:")
        rollDice(numDice, sides)
    }
}

/**
 * Demonstrates user interaction with the dice roller
 */
fun userInteractionDemo() {
    println("   Let's roll some custom dice!")
    
    print("   Enter number of dice (default 1): ")
    val numDiceInput = readln().trim()
    val numDice = if (numDiceInput.isBlank()) 1 else numDiceInput.toIntOrNull() ?: 1
    
    print("   Enter number of sides (default 6): ")
    val sidesInput = readln().trim()
    val sides = if (sidesInput.isBlank()) 6 else sidesInput.toIntOrNull() ?: 6
    
    println("\n   Your custom roll:")
    rollDice(numDice, sides)
    
    // Roll multiple times with same settings
    if (numDice > 1 || sides > 6) {
        println("   Let's roll 3 more times with the same settings:")
        repeat(3) {
            rollDice(numDice, sides)
        }
    }
}

/**
 * Advanced function with more detailed statistics
 */
fun rollDiceWithStats(numDice: Int = 1, sides: Int = 6, numRolls: Int = 1) {
    println("\n=== Advanced Dice Statistics ===")
    println("Configuration: $numRolls rolls of $numDice ${sides}-sided dice")
    
    val allRolls = List(numRolls) { 
        List(numDice) { (1..sides).random() }.sum() 
    }
    
    println("All totals: ${allRolls.joinToString()}")
    println("Average total: ${"%.2f".format(allRolls.average())}")
    println("Min total: ${allRolls.minOrNull()}")
    println("Max total: ${allRolls.maxOrNull()}")
    
    // Frequency distribution
    if (numRolls > 1) {
        val frequency = allRolls.groupingBy { it }.eachCount().toSortedMap()
        println("Frequency: $frequency")
    }
}

/**
 * Function to demonstrate parameter ambiguity and solutions
 */
fun demonstrateParameterAmbiguity() {
    println("\n=== Parameter Ambiguity Demonstration ===")
    
    println("Problem: rollDice(20) - Is this 20 dice or 20 sides?")
    println("Solution 1: Use named parameters")
    println("   rollDice(numDice = 20) - Clearly 20 dice")
    println("   rollDice(sides = 20) - Clearly 20 sides")
    
    println("Solution 2: Use different function names")
    println("   rollMultipleDice(20) - Clearly 20 dice")
    println("   rollSidedDie(20) - Clearly 20 sides")
}
