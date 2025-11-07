/**
 * Task 5.3.1 - Enhanced Dice Rolling Simulator
 * 
 * This program simulates rolling a die with a specified number of sides.
 * The rollDie() function now has a default parameter value.
 */

fun main() {
    println("=== Enhanced Dice Rolling Simulator ===")
    
    // Demonstrate the use of the default value
    println("\n1. Rolling with default sides (6):")
    val defaultRoll = rollDie()  // Using default value
    println("   Result: $defaultRoll")
    
    // Demonstrate with explicit value
    println("\n2. Rolling with 20 sides:")
    val twentySidedRoll = rollDie(20)  // Using explicit value
    println("   Result: $twentySidedRoll")
    
    // Let user choose whether to use default or specify sides
    println("\n3. User choice demonstration:")
    userChoiceDemonstration()
    
    // Show multiple default rolls
    println("\n4. Multiple default rolls:")
    repeat(5) {
        println("   Roll ${it + 1}: ${rollDie()}")
    }
    
    // Compare default vs custom
    println("\n5. Comparison: Default (6) vs Custom (12) sides")
    compareRolls()
}

/**
 * Rolls a die with the specified number of sides.
 * 
 * @param sides The number of sides on the die (default = 6)
 * @return A random number between 1 and the number of sides (inclusive)
 */
fun rollDie(sides: Int = 6): Int {
    if (sides <= 0) {
        throw IllegalArgumentException("Number of sides must be positive")
    }
    return (1..sides).random()
}

/**
 * Demonstrates user choice between default and custom sides
 */
fun userChoiceDemonstration() {
    print("Do you want to use the default 6-sided die? (y/n): ")
    val choice = readln().trim().lowercase()
    
    when {
        choice == "y" || choice == "yes" -> {
            println("   Using default 6-sided die...")
            println("   Result: ${rollDie()}")
        }
        choice == "n" || choice == "no" -> {
            print("   Enter number of sides: ")
            val customSides = readln().trim().toIntOrNull() ?: 6
            println("   Using $customSides-sided die...")
            println("   Result: ${rollDie(customSides)}")
        }
        else -> {
            println("   Invalid choice. Using default 6-sided die...")
            println("   Result: ${rollDie()}")
        }
    }
}

/**
 * Compares rolls between default and custom sides
 */
fun compareRolls() {
    val defaultSides = 6
    val customSides = 12
    
    println("   Default die (${defaultSides} sides):")
    repeat(3) {
        println("     Roll ${it + 1}: ${rollDie(defaultSides)}")
    }
    
    println("   Custom die (${customSides} sides):")
    repeat(3) {
        println("     Roll ${it + 1}: ${rollDie(customSides)}")
    }
}

/**
 * Enhanced version that can roll multiple dice
 */
fun rollMultipleDice(numDice: Int = 2, sides: Int = 6): List<Int> {
    return List(numDice) { rollDie(sides) }
}

/**
 * Demonstrates various uses of default parameters
 */
fun demonstrateDefaultParameters() {
    println("\n=== Default Parameter Demonstrations ===")
    
    // All defaults
    println("1. rollMultipleDice() -> ${rollMultipleDice()}")
    
    // Custom number of dice, default sides
    println("2. rollMultipleDice(3) -> ${rollMultipleDice(3)}")
    
    // Default number of dice, custom sides
    println("3. rollMultipleDice(sides = 20) -> ${rollMultipleDice(sides = 20)}")
    
    // All custom
    println("4. rollMultipleDice(5, 10) -> ${rollMultipleDice(5, 10)}")
}

/**
 * Function to show statistics for different die types
 */
fun showDieStatistics() {
    println("\n=== Die Statistics ===")
    
    val dieTypes = listOf(
        "D4" to 4,
        "D6" to 6,      // Default
        "D8" to 8,
        "D10" to 10,
        "D12" to 12,
        "D20" to 20
    )
    
    for ((name, sides) in dieTypes) {
        val rolls = List(10) { rollDie(sides) }
        val avg = rolls.average()
        println("$name (1-$sides): ${rolls.joinToString()} | Avg: ${"%.1f".format(avg)}")
    }
}

/**
 * Reads an integer from the user with a given prompt (from Task 5.1.2)
 */
fun readInt(prompt: String): Int {
    print(prompt)
    val input = readln().trim()
    
    return try {
        input.toInt()
    } catch (e: NumberFormatException) {
        println("Error: '$input' is not a valid integer. Using default value 6.")
        6
    }
}
