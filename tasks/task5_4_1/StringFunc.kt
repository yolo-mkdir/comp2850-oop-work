/**
 * Task 5.4.1 - String Extension Function
 * 
 * This program demonstrates extension functions for the String class.
 */

fun main() {
    println("=== String Length Checker ===")
    
    // Prompt for entry of a string
    print("Please enter a string: ")
    
    // Use readln() to read in that string
    val inputString = readln()
    
    // Use tooLong() to check whether it is too long
    val isTooLong = inputString.tooLong()
    
    // Display the results
    println("\nAnalysis Results:")
    println("Input: \"$inputString\"")
    println("Length: ${inputString.length} characters")
    
    if (isTooLong) {
        println("Status: ❌ TOO LONG (more than 20 characters)")
    } else {
        println("Status: ✅ OK (20 characters or less)")
    }
    
    // Additional demonstrations
    demonstrateVariousStrings()
    demonstrateEdgeCases()
}

/**
 * Extension function for String that checks if the string is too long.
 * 
 * @return true if the length of the string is greater than 20, otherwise false
 */
fun String.tooLong(): Boolean = this.length > 20

/**
 * Alternative implementation using explicit return
 */
fun String.tooLongExplicit(): Boolean {
    return this.length > 20
}

/**
 * More flexible version with custom length threshold
 */
fun String.tooLong(threshold: Int): Boolean = this.length > threshold

/**
 * Demonstrates the extension function with various strings
 */
fun demonstrateVariousStrings() {
    println("\n=== Demonstration with Various Strings ===")
    
    val testStrings = listOf(
        "Hello",
        "This is a medium string",
        "This string is definitely way too long for the limit we have set",
        "A",
        "Exactly 20 chars!!",  // 20 characters
        "Just over 20 chars",  // 21 characters
        "Short",
        "Another very long string that exceeds the twenty character limit by quite a margin"
    )
    
    for ((index, str) in testStrings.withIndex()) {
        val result = str.tooLong()
        val status = if (result) "❌ TOO LONG" else "✅ OK"
        println("${index + 1}. \"$str\"")
        println("   Length: ${str.length} -> $status")
    }
}

/**
 * Demonstrates edge cases and additional functionality
 */
fun demonstrateEdgeCases() {
    println("\n=== Edge Cases and Additional Features ===")
    
    // Empty string
    val empty = ""
    println("Empty string: \"$empty\"")
    println("   tooLong(): ${empty.tooLong()} (length: ${empty.length})")
    
    // Exactly 20 characters
    val exactly20 = "12345678901234567890"  // 20 characters
    println("Exactly 20 chars: \"$exactly20\"")
    println("   tooLong(): ${exactly20.tooLong()} (length: ${exactly20.length})")
    
    // Exactly 21 characters  
    val exactly21 = "123456789012345678901"  // 21 characters
    println("Exactly 21 chars: \"$exactly21\"")
    println("   tooLong(): ${exactly21.tooLong()} (length: ${exactly21.length})")
    
    // String with various characters
    val specialChars = "Hello! @#$%^&*() World 🌍"
    println("Special chars: \"$specialChars\"")
    println("   tooLong(): ${specialChars.tooLong()} (length: ${specialChars.length})")
    
    // Multi-line string
    val multiLine = """
        This is a multi-line string
        that spans several lines
        and might be too long
    """.trimIndent()
    println("Multi-line string:")
    println("   tooLong(): ${multiLine.tooLong()} (length: ${multiLine.length})")
}

/**
 * Additional extension functions for String
 */
fun String.isReasonable(): Boolean = this.length in 1..50

fun String.isVeryLong(): Boolean = this.length > 100

fun String.characterCountInfo(): String {
    return when {
        this.isEmpty() -> "Empty string"
        this.length <= 10 -> "Short (${this.length} chars)"
        this.length <= 20 -> "Medium (${this.length} chars)"
        this.length <= 50 -> "Long (${this.length} chars)"
        else -> "Very long (${this.length} chars)"
    }
}

/**
 * Function to test the flexible version with custom threshold
 */
fun demonstrateCustomThreshold() {
    println("\n=== Custom Threshold Demonstration ===")
    
    val testString = "This is a test string"
    
    val thresholds = listOf(10, 15, 20, 25, 30)
    
    for (threshold in thresholds) {
        val result = testString.tooLong(threshold)
        println("Length: ${testString.length}, Threshold: $threshold -> Too long: $result")
    }
}

/**
 * Interactive demonstration with user input
 */
fun interactiveDemo() {
    println("\n=== Interactive Demonstration ===")
    
    while (true) {
        print("Enter a string to check (or 'quit' to exit): ")
        val input = readln().trim()
        
        if (input.equals("quit", ignoreCase = true)) {
            println("Goodbye!")
            break
        }
        
        println("Length: ${input.length}")
        println("Too long (>20): ${input.tooLong()}")
        println("Character info: ${input.characterCountInfo()}")
        println("-".repeat(40))
    }
}
