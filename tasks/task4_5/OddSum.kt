/**
 * Task 4.5 - Odd Number Sum Calculator
 * 
 * This program calculates the sum of all odd integers between 1 and a given limit.
 */

import java.util.Scanner

fun main() {
    println("=== Odd Number Sum Calculator ===")
    
    // Prompt user for integer limit
    print("Enter an integer limit: ")
    val input = readln().trim()
    
    // Validate and parse the input
    val limit = try {
        input.toLong()  // Use Long to support large values
    } catch (e: NumberFormatException) {
        println("Error: Please enter a valid integer.")
        return
    }
    
    // Validate the limit is positive
    if (limit <= 0) {
        println("Error: Please enter a positive integer.")
        return
    }
    
    if (limit > Int.MAX_VALUE) {
        println("Warning: Very large limit entered. Calculation may take a while.")
    }
    
    // Calculate the sum of odd numbers
    val sum = calculateOddSum(limit)
    
    // Display the result
    println("\nSum of all odd numbers from 1 to $limit: $sum")
    
    // Optional: Show calculation details
    displayCalculationDetails(limit, sum)
}

fun calculateOddSum(limit: Long): Long {
    var sum: Long = 0  // Use Long to handle large sums
    
    // Method 1: Using for loop with step
    println("\nCalculating sum using for loop...")
    for (i in 1..limit step 2) {
        sum += i
    }
    
    return sum
}

// Alternative method using while loop
fun calculateOddSumWhile(limit: Long): Long {
    var sum: Long = 0
    var current: Long = 1
    
    while (current <= limit) {
        sum += current
        current += 2
    }
    
    return sum
}

// Mathematical formula approach (most efficient for large numbers)
fun calculateOddSumFormula(limit: Long): Long {
    // Formula: sum of first n odd numbers = n²
    // Number of odd numbers from 1 to limit = (limit + 1) / 2
    val n = (limit + 1) / 2
    return n * n
}

fun displayCalculationDetails(limit: Long, sum: Long) {
    val numberOfOdds = (limit + 1) / 2
    println("Number of odd numbers: $numberOfOdds")
    
    // Verify using mathematical formula
    val formulaSum = calculateOddSumFormula(limit)
    println("Verification using formula: $formulaSum")
    
    if (sum == formulaSum) {
        println("✓ Calculation verified!")
    } else {
        println("✗ Calculation mismatch!")
    }
    
    // Show some examples if limit is small
    if (limit <= 20) {
        val oddNumbers = (1..limit step 2).toList()
        println("Odd numbers: ${oddNumbers.joinToString(" + ")} = $sum")
    }
}

// Function to test with various limits
fun testVariousLimits() {
    println("\n=== Testing Various Limits ===")
    
    val testLimits = listOf(5L, 10L, 20L, 100L, 1000L, 1000000L)
    
    for (limit in testLimits) {
        val sum = calculateOddSumFormula(limit)  // Use formula for efficiency
        println("Limit: $limit -> Sum: $sum")
    }
}
