/**
 * Task 6.4.2 - Anagram Checker
 * 
 * This program checks if two strings are anagrams of each other.
 * Anagrams are words or phrases formed by rearranging the letters of another.
 */

fun areAnagrams(str1: String, str2: String): Boolean {
    // Clean the strings: remove spaces and convert to lowercase
    val cleanStr1 = str1.replace("\\s".toRegex(), "").lowercase()
    val cleanStr2 = str2.replace("\\s".toRegex(), "").lowercase()
    
    // If lengths are different, they can't be anagrams
    if (cleanStr1.length != cleanStr2.length) {
        return false
    }
    
    // Count character frequencies
    val charCount1 = cleanStr1.groupingBy { it }.eachCount()
    val charCount2 = cleanStr2.groupingBy { it }.eachCount()
    
    // Compare character frequencies
    return charCount1 == charCount2
}

/**
 * Main function to run the anagram checker interactively
 */
fun main() {
    println("=== Anagram Checker ===")
    println("Enter two strings to check if they are anagrams")
    println("Type 'quit' to exit the program")
    
    while (true) {
        print("\nEnter first string: ")
        val input1 = readlnOrNull()?.trim() ?: ""
        
        if (input1.equals("quit", ignoreCase = true)) {
            println("Goodbye!")
            break
        }
        
        print("Enter second string: ")
        val input2 = readlnOrNull()?.trim() ?: ""
        
        if (input2.equals("quit", ignoreCase = true)) {
            println("Goodbye!")
            break
        }
        
        val result = areAnagrams(input1, input2)
        
        if (result) {
            println("✅ '$input1' and '$input2' ARE anagrams")
        } else {
            println("❌ '$input1' and '$input2' are NOT anagrams")
        }
    }
}
