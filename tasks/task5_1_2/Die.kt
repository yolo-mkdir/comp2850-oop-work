/**
 * Task 5.1.2 - Dice Rolling Simulator
 * 
 * This program simulates rolling a die with a specified number of sides.
 */

import java.util.Scanner

fun main() {
    println("=== Dice Rolling Simulator ===")
    
    // Use readInt() to obtain the number of die sides from the user
    val sides = readInt("Enter the number of sides for the die: ")
    
    // Validate the number of sides
    if (sides <= 0) {
        println("Error: Number of sides must be positive.")
        return
    }
    
    if (sides == 1) {
        println("Warning: A 1-sided die always rolls 1!")
    }
    
    // Invoke rollDie() with the specified number of die sides
    val result = rollDie(sides)
    
    // Display the result
    println("\nRolling a $sides-sided die...")
    println("Result: $result")
    
    // Optional: Roll multiple times
    if (sides > 1) {
        println("\n--- Multiple Rolls ---")
        repeat(5) {
            println("Roll ${it + 1}: ${rollDie(sides)}")
        }
        
        // Statistics for multiple rolls
        if (sides <= 20) {
            displayStatistics(sides)
        }
    }
}

/**
 * Reads an integer from the user with a given prompt.
 * 
 * @param prompt The message to display to the user
 * @return The integer entered by the user
 */
fun readInt(prompt: String): Int {
    print(prompt)
    val input = readln().trim()
    
    return try {
        input.toInt()
    } catch (e: NumberFormatException) {
        println("Error: '$input' is not a valid integer. Using default value 6.")
        6  // Default to a standard die
    }
}

/**
 * Enhanced version of readInt with validation
 */
fun readIntWithValidation(prompt: String, min: Int = 1, max: Int = 100): Int {
    while (true) {
        print(prompt)
        val input = readln().trim()
        
        try {
            val value = input.toInt()
            if (value in min..max) {
                return value
            } else {
                println("Error: Please enter a number between $min and $max.")
            }
        } catch (e: NumberFormatException) {
            println("Error: '$input' is not a valid integer. Please try again.")
        }
    }
}

/**
 * Rolls a die with the specified number of sides.
 * 
 * @param sides The number of sides on the die
 * @return A random number between 1 and the number of sides (inclusive)
 */
fun rollDie(sides: Int): Int {
    if (sides <= 0) {
        throw IllegalArgumentException("Number of sides must be positive")
    }
    return (1..sides).random()
}

/**
 * Displays statistics for multiple die rolls
 */
fun displayStatistics(sides: Int) {
    println("\n--- Statistics (1000 rolls) ---")
    val rolls = IntArray(sides) { 0 }
    
    // Roll 1000 times and count frequencies
    repeat(1000) {
        val roll = rollDie(sides)
        rolls[roll - 1]++
    }
    
    // Display frequency distribution
    println("Frequency distribution:")
    for (i in 0 until sides) {
        val side = i + 1
        val frequency = rolls[i]
        val percentage = (frequency / 10.0)
        val bars = "█".repeat((percentage).toInt())
        println("Side $side: $frequency times ($percentage%) $bars")
    }
    
    // Calculate and display expected vs actual
    val expectedFrequency = 1000.0 / sides
    println("\nExpected frequency per side: ${"%.1f".format(expectedFrequency)}")
}

/**
 * Simulates rolling multiple dice
 */
fun rollMultipleDice(numDice: Int, sides: Int): List<Int> {
    return List(numDice) { rollDie(sides) }
}

/**
 * Function to demonstrate different die types
 */
fun demonstrateDieTypes() {
    println("\n=== Common Die Types ===")
    
    val dieTypes = mapOf(
        "D4" to 4,
        "D6" to 6,
        "D8" to 8,
        "D10" to 10,
        "D12" to 12,
        "D20" to 20,
        "D100" to 100
    )
    
    for ((name, sides) in dieTypes) {
        println("$name: ${rollDie(sides)}")
    }
}
