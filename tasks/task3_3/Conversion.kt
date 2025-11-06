/**
 * Task 3.3 - Input Conversion
 * 
 * This program demonstrates handling and converting various types of input.
 */

import java.util.Scanner

fun main() {
    println("=== Task 3.3 - Input Conversion ===")
    println("Enter values to convert (press Ctrl+D to exit):")
    
    val scanner = Scanner(System.`in`)
    
    while (scanner.hasNextLine()) {
        val input = scanner.nextLine().trim()
        
        if (input.isBlank()) continue
        if (input.equals("exit", ignoreCase = true)) break
        
        println("\nProcessing input: '$input'")
        processInput(input)
        println("-".repeat(50))
    }
    
    println("\nProgram ended.")
}

fun processInput(input: String) {
    // Try different conversion approaches
    tryIntegerConversion(input)
    tryLongConversion(input)
    tryDoubleConversion(input)
    tryBooleanConversion(input)
    analyzeString(input)
}

fun tryIntegerConversion(input: String) {
    println("Integer conversion:")
    try {
        val intValue = input.toInt()
        println("  ✓ Success: $intValue")
        
        // Additional integer operations
        println("  Binary: ${intValue.toString(2)}")
        println("  Hexadecimal: 0x${intValue.toString(16).uppercase()}")
        println("  Octal: 0${intValue.toString(8)}")
        
        // Range checking
        when (intValue) {
            in Byte.MIN_VALUE..Byte.MAX_VALUE -> println("  Fits in: Byte")
            in Short.MIN_VALUE..Short.MAX_VALUE -> println("  Fits in: Short")
            in Int.MIN_VALUE..Int.MAX_VALUE -> println("  Fits in: Int")
        }
    } catch (e: NumberFormatException) {
        println("  ✗ Failed: Not a valid integer")
    }
}

fun tryLongConversion(input: String) {
    println("Long conversion:")
    try {
        val longValue = input.toLong()
        println("  ✓ Success: $longValue")
        
        // Additional long operations
        println("  Binary: ${longValue.toString(2)}")
        println("  Hexadecimal: 0x${longValue.toString(16).uppercase()}")
        println("  Octal: 0${longValue.toString(8)}")
        
        // Range checking
        when {
            longValue >= Int.MIN_VALUE.toLong() && longValue <= Int.MAX_VALUE.toLong() -> 
                println("  Also fits in: Int")
            else -> println("  Requires: Long")
        }
    } catch (e: NumberFormatException) {
        println("  ✗ Failed: Not a valid long")
    }
}

fun tryDoubleConversion(input: String) {
    println("Double conversion:")
    try {
        val doubleValue = input.toDouble()
        println("  ✓ Success: $doubleValue")
        
        // Additional double operations
        println("  Scientific: ${"%.2e".format(doubleValue)}")
        println("  Rounded: ${"%.2f".format(doubleValue)}")
        
        // Special value checks
        when {
            doubleValue.isNaN() -> println("  Type: NaN")
            doubleValue.isInfinite() -> println("  Type: Infinite")
            doubleValue == 0.0 -> println("  Type: Zero")
            doubleValue > 0 -> println("  Sign: Positive")
            else -> println("  Sign: Negative")
        }
    } catch (e: NumberFormatException) {
        println("  ✗ Failed: Not a valid double")
    }
}

fun tryBooleanConversion(input: String) {
    println("Boolean conversion:")
    val lowerInput = input.lowercase()
    
    val trueValues = setOf("true", "yes", "1", "on", "y")
    val falseValues = setOf("false", "no", "0", "off", "n")
    
    when {
        trueValues.contains(lowerInput) -> println("  ✓ Boolean: true")
        falseValues.contains(lowerInput) -> println("  ✓ Boolean: false")
        else -> println("  ✗ Not a recognizable boolean value")
    }
}

fun analyzeString(input: String) {
    println("String analysis:")
    println("  Length: ${input.length} characters")
    println("  Trimmed length: ${input.trim().length} characters")
    
    // Character type analysis
    val digitCount = input.count { it.isDigit() }
    val letterCount = input.count { it.isLetter() }
    val whitespaceCount = input.count { it.isWhitespace() }
    val otherCount = input.length - digitCount - letterCount - whitespaceCount
    
    println("  Contains: $digitCount digits, $letterCount letters, $whitespaceCount whitespace, $otherCount other")
    
    // Word count (simple version)
    val words = input.trim().split("\\s+".toRegex()).filter { it.isNotBlank() }
    println("  Word count: ${words.size}")
    
    // Case analysis
    when {
        input.all { it.isUpperCase() } -> println("  Case: ALL UPPERCASE")
        input.all { it.isLowerCase() } -> println("  Case: all lowercase")
        input.isNotEmpty() && input[0].isUpperCase() && input.drop(1).all { it.isLowerCase() } -> 
            println("  Case: Capitalized")
        else -> println("  Case: Mixed")
    }
    
    // Special number word conversion (basic)
    val numberWords = mapOf(
        "zero" to 0, "one" to 1, "two" to 2, "three" to 3, "four" to 4,
        "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
        "ten" to 10, "eleven" to 11, "twelve" to 12, "thirteen" to 13,
        "fourteen" to 14, "fifteen" to 15, "sixteen" to 16, "seventeen" to 17,
        "eighteen" to 18, "nineteen" to 19, "twenty" to 20,
        "thirty" to 30, "forty" to 40, "fifty" to 50, "sixty" to 60,
        "seventy" to 70, "eighty" to 80, "ninety" to 90
    )
    
    val lowerInput = input.lowercase()
    numberWords[lowerInput]?.let { number ->
        println("  ✓ Number word detected: '$input' = $number")
    }
}

// Extension function for safe conversion
fun String.toIntSafe(default: Int = 0): Int {
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        default
    }
}

fun String.toDoubleSafe(default: Double = 0.0): Double {
    return try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        default
    }
}
