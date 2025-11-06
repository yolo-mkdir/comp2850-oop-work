/**
 * Task 4.3 - Module Grade Calculator
 * 
 * This program calculates the module grade based on three marks.
 */

import kotlin.math.roundToInt

fun main(args: Array<String>) {
    // Check that exactly three command line arguments have been supplied
    if (args.size != 3) {
        println("Error: Please provide exactly three marks as command line arguments.")
        println("Usage: kotlin ModuleGradeKt <mark1> <mark2> <mark3>")
        return
    }
    
    // Parse the marks and validate they are integers
    val marks = try {
        args.map { it.toInt() }
    } catch (e: NumberFormatException) {
        println("Error: All marks must be valid integers.")
        return
    }
    
    // Validate marks are within 0-100 range
    val invalidMarks = marks.filter { it < 0 || it > 100 }
    if (invalidMarks.isNotEmpty()) {
        println("Error: All marks must be between 0 and 100.")
        println("Invalid marks: $invalidMarks")
        return
    }
    
    // Calculate the rounded average
    val average = marks.average().roundToInt()
    
    // Determine the module grade using when expression
    val grade = when (average) {
        in 70..100 -> "Distinction"
        in 40..69 -> "Pass"
        in 0..39 -> "Fail"
        else -> "Invalid average" // This should never happen due to validation
    }
    
    // Display the results
    println("=== Module Grade Calculator ===")
    println("Marks: ${marks.joinToString(", ")}")
    println("Average: $average")
    println("Grade: $grade")
}

// Alternative implementation with more detailed output
fun calculateDetailedGrade(marks: List<Int>): String {
    val average = marks.average().roundToInt()
    
    return when (average) {
        in 90..100 -> "Distinction (Excellent)"
        in 80..89 -> "Distinction (Very Good)"
        in 70..79 -> "Distinction (Good)"
        in 60..69 -> "Pass (Good)"
        in 50..59 -> "Pass (Satisfactory)"
        in 40..49 -> "Pass (Adequate)"
        in 0..39 -> "Fail (Needs Improvement)"
        else -> "Invalid"
    }
}
