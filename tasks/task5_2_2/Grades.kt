/**
 * Task 5.2.2 - Exam Grades Calculator
 * 
 * This program processes exam marks from command line arguments and assigns grades.
 */

fun main(args: Array<String>) {
    println("=== Exam Grades Calculator ===")
    
    // Check if any arguments were provided
    if (args.isEmpty()) {
        println("Usage: kotlin GradesKt <mark1> <mark2> <mark3> ...")
        println("Please provide exam marks as command line arguments.")
        return
    }
    
    println("Processing ${args.size} exam mark(s):")
    println("-".repeat(50))
    
    var validCount = 0
    var invalidCount = 0
    var distinctionCount = 0
    var passCount = 0
    var failCount = 0
    
    // Iterate over the program's command line arguments using a for loop
    for ((index, arg) in args.withIndex()) {
        // Convert each argument to an Int, representing an exam mark
        val mark = try {
            arg.toInt()
        } catch (e: NumberFormatException) {
            println("${index + 1}. '$arg' -> ERROR: Not a valid integer")
            invalidCount++
            continue
        }
        
        // Validate the mark is within 0-100 range
        if (mark !in 0..100) {
            println("${index + 1}. $mark -> ERROR: Mark must be between 0 and 100")
            invalidCount++
            continue
        }
        
        validCount++
        
        // Invoke grade() on the exam mark, to determine the corresponding grade
        val examGrade = grade(mark)
        
        // Count grades for statistics
        when (examGrade) {
            "Distinction" -> distinctionCount++
            "Pass" -> passCount++
            "Fail" -> failCount++
        }
        
        // Prints both the mark and the grade
        println("${index + 1}. $mark -> $examGrade")
    }
    
    // Display summary statistics
    displaySummary(validCount, invalidCount, distinctionCount, passCount, failCount)
}

/**
 * Determines the grade for an exam mark using an expression body.
 * 
 * @param mark The exam mark (0-100)
 * @return The corresponding grade as a String
 */
fun grade(mark: Int): String = when {
    mark >= 70 -> "Distinction"
    mark >= 40 -> "Pass"
    else -> "Fail"
}

/**
 * Alternative implementation with more detailed grades
 */
fun detailedGrade(mark: Int): String = when (mark) {
    in 90..100 -> "Distinction (A+)"
    in 80..89 -> "Distinction (A)"
    in 70..79 -> "Distinction (B+)"
    in 60..69 -> "Pass (B)"
    in 50..59 -> "Pass (C)"
    in 40..49 -> "Pass (D)"
    else -> "Fail (F)"
}

/**
 * Displays summary statistics
 */
fun displaySummary(validCount: Int, invalidCount: Int, distinctionCount: Int, passCount: Int, failCount: Int) {
    println("-".repeat(50))
    println("SUMMARY:")
    println("Total arguments processed: ${validCount + invalidCount}")
    println("Valid marks: $validCount")
    println("Invalid marks: $invalidCount")
    
    if (validCount > 0) {
        val total = validCount
        println("\nGrade Distribution:")
        println("Distinction: $distinctionCount (${"%.1f".format(distinctionCount * 100.0 / total)}%)")
        println("Pass: $passCount (${"%.1f".format(passCount * 100.0 / total)}%)")
        println("Fail: $failCount (${"%.1f".format(failCount * 100.0 / total)}%)")
        
        // Determine overall performance
        val passRate = (distinctionCount + passCount) * 100.0 / total
        println("\nOverall Pass Rate: ${"%.1f".format(passRate)}%")
        
        when {
            passRate >= 90 -> println("Excellent performance!")
            passRate >= 80 -> println("Very good performance!")
            passRate >= 70 -> println("Good performance!")
            passRate >= 60 -> println("Satisfactory performance!")
            else -> println("Needs improvement.")
        }
    }
}

/**
 * Function to test with sample data
 */
fun testWithSampleData() {
    println("\n=== Sample Data Test ===")
    val sampleMarks = arrayOf("85", "92", "78", "45", "32", "67", "55", "88", "29", "71")
    
    // Create a temporary main function for sample data
    val tempArgs = sampleMarks
    println("Processing ${tempArgs.size} sample marks:")
    println("-".repeat(50))
    
    for ((index, arg) in tempArgs.withIndex()) {
        val mark = arg.toInt()
        val examGrade = grade(mark)
        println("${index + 1}. $mark -> $examGrade")
    }
}

/**
 * Function to demonstrate boundary cases
 */
fun demonstrateBoundaries() {
    println("\n=== Grade Boundary Test ===")
    val boundaries = listOf(0, 39, 40, 69, 70, 100)
    
    for (mark in boundaries) {
        println("Mark: $mark -> Grade: ${grade(mark)}")
    }
}
