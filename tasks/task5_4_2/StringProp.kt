/**
 * Task 5.4.2 - String Extension Property
 * 
 * This program demonstrates extension properties for the String class.
 */

fun main() {
    println("=== String Length Checker (Extension Property) ===")
    
    // Prompt for entry of a string
    print("Please enter a string: ")
    
    // Use readln() to read in that string
    val inputString = readln()
    
    // Use the tooLong extension property to check whether it is too long
    val isTooLong = inputString.tooLong
    
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
 * Extension property for String that checks if the string is too long.
 * 
 * Returns true if the length of the string is greater than 20, otherwise false
 */
val String.tooLong: Boolean
    get() = this.length > 20

/**
 * Alternative extension property with explicit getter
 */
val String.isTooLongExplicit: Boolean
    get() {
        return this.length > 20
    }

/**
 * More flexible extension property with custom length threshold
 * Note: Properties can't have parameters, so we use a function for this
 */
fun String.tooLong(threshold: Int): Boolean = this.length > threshold

/**
 * Additional extension properties for String analysis
 */
val String.isReasonable: Boolean
    get() = this.length in 1..50

val String.isVeryLong: Boolean
    get() = this.length > 100

val String.characterCategory: String
    get() = when {
        this.isEmpty() -> "Empty"
        this.length <= 10 -> "Short"
        this.length <= 20 -> "Medium" 
        this.length <= 50 -> "Long"
        else -> "Very long"
    }

val String.lengthInfo: String
    get() = "$characterCategory (${this.length} chars)"

/**
 * Demonstrates the extension property with various strings
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
        val result = str.tooLong  // Using property syntax (no parentheses)
        val status = if (result) "❌ TOO LONG" else "✅ OK"
        println("${index + 1}. \"$str\"")
        println("   Length: ${str.length} -> $status")
        println("   Category: ${str.characterCategory}")
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
    println("   tooLong: ${empty.tooLong} (length: ${empty.length})")
    println("   isReasonable: ${empty.isReasonable}")
    
    // Exactly 20 characters
    val exactly20 = "12345678901234567890"  // 20 characters
    println("Exactly 20 chars: \"$exactly20\"")
    println("   tooLong: ${exactly20.tooLong} (length: ${exactly20.length})")
    println("   characterCategory: ${exactly20.characterCategory}")
    
    // Exactly 21 characters  
    val exactly21 = "123456789012345678901"  // 21 characters
    println("Exactly 21 chars: \"$exactly21\"")
    println("   tooLong: ${exactly21.tooLong} (length: ${exactly21.length})")
    println("   characterCategory: ${exactly21.characterCategory}")
    
    // String with various characters
    val specialChars = "Hello! @#$%^&*() World 🌍"
    println("Special chars: \"$specialChars\"")
    println("   tooLong: ${specialChars.tooLong} (length: ${specialChars.length})")
    println("   lengthInfo: ${specialChars.lengthInfo}")
    
    // Multi-line string
    val multiLine = """
        This is a multi-line string
        that spans several lines
        and might be too long
    """.trimIndent()
    println("Multi-line string:")
    println("   tooLong: ${multiLine.tooLong} (length: ${multiLine.length})")
    println("   isVeryLong: ${multiLine.isVeryLong}")
}

/**
 * Function to compare function vs property syntax
 */
fun demonstrateSyntaxDifference() {
    println("\n=== Function vs Property Syntax ===")
    
    val testString = "This is a test string"
    
    // Old function syntax (from Task 5.4.1)
    // fun String.tooLong(): Boolean = this.length > 20
    // val result1 = testString.tooLong()  // Function call with parentheses
    
    // New property syntax (Task 5.4.2)
    // val String.tooLong: Boolean get() = this.length > 20  
    val result2 = testString.tooLong  // Property access without parentheses
    
    println("String: \"$testString\"")
    println("Length: ${testString.length}")
    println("Using extension property: tooLong = $result2")
    
    // Demonstrate that properties are accessed without parentheses
    println("\nProperty access examples:")
    println("  testString.tooLong = ${testString.tooLong}")
    println("  testString.isReasonable = ${testString.isReasonable}")
    println("  testString.characterCategory = \"${testString.characterCategory}\"")
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
        println("Too long (>20): ${input.tooLong}")  // Property syntax
        println("Character info: ${input.lengthInfo}")
        println("-".repeat(40))
    }
}

/**
 * Additional utility extension properties
 */
val String.isPalindrome: Boolean
    get() {
        val clean = this.lowercase().filter { it.isLetterOrDigit() }
        return clean == clean.reversed()
    }

val String.wordCount: Int
    get() = this.trim().split("\\s+".toRegex()).count { it.isNotBlank() }

val String.containsDigits: Boolean
    get() = this.any { it.isDigit() }
