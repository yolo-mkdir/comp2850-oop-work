/**
 * Task 7.7.1 - Statistics Calculator
 * 
 * This program prompts the user to enter a series of floating-point values,
 * stores them in a list, and computes the median value.
 */

import kotlin.math.*

/**
 * Prompts the user to enter a series of floating-point values and returns them as a list.
 * Uses Kotlin's buildList() function to construct the list.
 * 
 * @return List of Double values entered by the user
 */
fun readNumbers(): List<Double> {
    println("=== Statistics Calculator ===")
    println("Enter floating-point numbers (one per line)")
    println("Enter 'done' to finish input")
    println("Enter 'cancel' to exit program")
    
    return buildList {
        while (true) {
            print("Enter a number: ")
            val input = readlnOrNull()?.trim()
            
            when {
                input == null -> {
                    println("No input received. Exiting.")
                    break
                }
                input.equals("done", ignoreCase = true) -> {
                    println("Input completed. Total numbers entered: ${this.size}")
                    break
                }
                input.equals("cancel", ignoreCase = true) -> {
                    println("Program cancelled.")
                    return emptyList()
                }
                input.isEmpty() -> {
                    println("Please enter a number or 'done' to finish.")
                    continue
                }
                else -> {
                    try {
                        val number = input.toDouble()
                        add(number)
                        println("Added: $number (Total: ${this.size})")
                    } catch (e: NumberFormatException) {
                        println("Error: '$input' is not a valid number. Please try again.")
                    }
                }
            }
        }
    }
}

/**
 * Computes the median of a list of floating-point values.
 * 
 * @param numbers List of Double values
 * @return The median value as Double
 * @throws IllegalArgumentException if the list is empty
 */
fun computeMedian(numbers: List<Double>): Double {
    if (numbers.isEmpty()) {
        throw IllegalArgumentException("Cannot compute median of an empty list")
    }
    
    // Create a sorted copy of the list
    val sortedNumbers = numbers.sorted()
    
    return if (sortedNumbers.size % 2 == 1) {
        // Odd number of elements: median is the middle element
        sortedNumbers[sortedNumbers.size / 2]
    } else {
        // Even number of elements: median is average of two middle elements
        val mid = sortedNumbers.size / 2
        (sortedNumbers[mid - 1] + sortedNumbers[mid]) / 2.0
    }
}

/**
 * Extension function to compute mean (average) of a list of numbers
 */
fun List<Double>.mean(): Double {
    if (isEmpty()) return 0.0
    return sum() / size
}

/**
 * Extension function to compute standard deviation of a list of numbers
 */
fun List<Double>.standardDeviation(): Double {
    if (isEmpty()) return 0.0
    val mean = this.mean()
    val variance = map { (it - mean).pow(2) }.sum() / size
    return sqrt(variance)
}

/**
 * Extension function to compute range of a list of numbers
 */
fun List<Double>.range(): Double {
    if (isEmpty()) return 0.0
    return maxOrNull()!! - minOrNull()!!
}

/**
 * Extension function to format numbers for nice display
 */
fun List<Double>.formatStats(): String {
    if (isEmpty()) return "No data available"
    
    return """
        Count: ${size}
        Sorted: ${sorted().joinToString(", ")}
        Min: ${minOrNull()!!.format(2)}
        Max: ${maxOrNull()!!.format(2)}
        Mean: ${mean().format(2)}
        Median: ${computeMedian(this).format(2)}
        Std Dev: ${standardDeviation().format(2)}
        Range: ${range().format(2)}
    """.trimIndent()
}

/**
 * Extension function to format Double values with specified decimal places
 */
fun Double.format(decimals: Int): String {
    return "%.${decimals}f".format(this)
}

/**
 * Main function that orchestrates the statistics calculation
 */
fun main() {
    try {
        // Read numbers from user input
        val numbers = readNumbers()
        
        if (numbers.isEmpty()) {
            println("No numbers were entered. Exiting.")
            return
        }
        
        // Display basic information
        println("\n=== Results ===")
        println("Numbers entered: ${numbers.joinToString(", ")}")
        println("Count: ${numbers.size}")
        
        // Compute and display statistics
        val median = computeMedian(numbers)
        println("Median: ${median.format(2)}")
        
        // Display additional statistics using extension functions
        println("\n=== Detailed Statistics ===")
        println(numbers.formatStats())
        
        // Demonstrate individual extension functions
        println("\n=== Individual Statistics ===")
        println("Mean (average): ${numbers.mean().format(2)}")
        println("Standard Deviation: ${numbers.standardDeviation().format(2)}")
        println("Range: ${numbers.range().format(2)}")
        
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }
}

/**
 * Additional demonstration function showing different use cases
 */
fun demonstrateWithSampleData() {
    println("\n=== Demonstration with Sample Data ===")
    
    val sampleData1 = listOf(1.0, 2.0, 3.0, 4.0, 5.0)  // Odd number of elements
    val sampleData2 = listOf(1.0, 2.0, 3.0, 4.0)       // Even number of elements
    val sampleData3 = listOf(10.5, 2.1, 8.7, 4.3, 6.9) // Unsorted data
    
    println("Sample 1: $sampleData1")
    println("Median: ${computeMedian(sampleData1).format(2)}")
    
    println("\nSample 2: $sampleData2") 
    println("Median: ${computeMedian(sampleData2).format(2)}")
    
    println("\nSample 3: $sampleData3")
    println("Median: ${computeMedian(sampleData3).format(2)}")
    
    println("\nSample 3 Detailed:")
    println(sampleData3.formatStats())
}

/**
 * Function to run automated tests
 */
fun runTests() {
    println("\n=== Running Tests ===")
    
    // Test 1: Empty list
    try {
        computeMedian(emptyList())
        println("❌ Test 1 Failed: Should have thrown exception for empty list")
    } catch (e: IllegalArgumentException) {
        println("✅ Test 1 Passed: Correctly handled empty list")
    }
    
    // Test 2: Single element
    val single = listOf(5.0)
    val medianSingle = computeMedian(single)
    if (medianSingle == 5.0) {
        println("✅ Test 2 Passed: Single element median = $medianSingle")
    } else {
        println("❌ Test 2 Failed: Expected 5.0, got $medianSingle")
    }
    
    // Test 3: Two elements (even count)
    val twoElements = listOf(3.0, 1.0)
    val medianTwo = computeMedian(twoElements)
    if (medianTwo == 2.0) {
        println("✅ Test 3 Passed: Two elements median = $medianTwo")
    } else {
        println("❌ Test 3 Failed: Expected 2.0, got $medianTwo")
    }
    
    // Test 4: Three elements (odd count)
    val threeElements = listOf(5.0, 1.0, 3.0)
    val medianThree = computeMedian(threeElements)
    if (medianThree == 3.0) {
        println("✅ Test 4 Passed: Three elements median = $medianThree")
    } else {
        println("❌ Test 4 Failed: Expected 3.0, got $medianThree")
    }
}
