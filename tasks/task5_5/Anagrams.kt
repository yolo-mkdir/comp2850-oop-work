/**
 * Task 5.5 - Anagram Detector with Infix Extension Function
 * 
 * This program checks if two strings are anagrams of each other
 * using infix extension function notation.
 */

fun main() {
    println("=== Anagram Detector (Infix Notation) ===")
    
    // Read first string from user
    print("Enter the first string: ")
    val string1 = readln().trim()
    
    // Read second string from user  
    print("Enter the second string: ")
    val string2 = readln().trim()
    
    // Check if the strings are anagrams using INFIX NOTATION
    val result = string1 anagramOf string2
    
    // Display the results
    println("\nComparison Results:")
    println("String 1: '$string1'")
    println("String 2: '$string2'")
    
    if (result) {
        println("✓ These strings ARE anagrams!")
    } else {
        println("✗ These strings are NOT anagrams.")
    }
    
    // Display additional analysis
    displayAnalysis(string1, string2)
    
    // Demonstrate various infix usage styles
    demonstrateInfixNotation()
}

/**
 * Infix extension function for String that checks if this string is an anagram of another.
 * 
 * @param other The other string to compare with
 * @return true if the strings are anagrams, false otherwise
 */
infix fun String.anagramOf(other: String): Boolean {
    // Normalize the strings: convert to lowercase and remove non-letter characters
    val normalized1 = this.lowercase().filter { it.isLetter() }
    val normalized2 = other.lowercase().filter { it.isLetter() }
    
    // If lengths are different after normalization, they can't be anagrams
    if (normalized1.length != normalized2.length) {
        return false
    }
    
    // Count character frequencies for both strings
    val freq1 = normalized1.groupingBy { it }.eachCount()
    val freq2 = normalized2.groupingBy { it }.eachCount()
    
    // Compare the frequency maps
    return freq1 == freq2
}

/**
 * Alternative implementation using sorting (also as infix function)
 */
infix fun String.anagramOfUsingSorting(other: String): Boolean {
    val normalized1 = this.lowercase().filter { it.isLetter() }.toCharArray().sorted()
    val normalized2 = other.lowercase().filter { it.isLetter() }.toCharArray().sorted()
    
    return normalized1 == normalized2
}

/**
 * Displays detailed analysis of the strings
 */
fun displayAnalysis(s1: String, s2: String) {
    println("\n--- Detailed Analysis ---")
    
    val normalized1 = s1.lowercase().filter { it.isLetter() }
    val normalized2 = s2.lowercase().filter { it.isLetter() }
    
    println("Normalized string 1: '$normalized1' (${normalized1.length} letters)")
    println("Normalized string 2: '$normalized2' (${normalized2.length} letters)")
    
    if (normalized1.length != normalized2.length) {
        println("Different lengths → NOT anagrams")
        return
    }
    
    // Show character frequencies
    println("\nCharacter frequencies:")
    val freq1 = normalized1.groupingBy { it }.eachCount().toSortedMap()
    val freq2 = normalized2.groupingBy { it }.eachCount().toSortedMap()
    
    println("String 1: $freq1")
    println("String 2: $freq2")
    
    // Check if frequencies match using infix notation
    val frequenciesMatch = freq1 anagramMapMatches freq2
    if (frequenciesMatch) {
        println("✓ Character frequencies match!")
    } else {
        println("✗ Character frequencies don't match!")
        
        // Show differences
        val diff1 = freq1.filter { (char, count) -> freq2[char] != count }
        val diff2 = freq2.filter { (char, count) -> freq1[char] != count }
        
        if (diff1.isNotEmpty()) {
            println("Differences in string 1: $diff1")
        }
        if (diff2.isNotEmpty()) {
            println("Differences in string 2: $diff2")
        }
    }
}

/**
 * Demonstrates various ways to use infix notation
 */
fun demonstrateInfixNotation() {
    println("\n=== Infix Notation Demonstration ===")
    
    val knownAnagrams = listOf(
        "listen" to "silent",
        "evil" to "vile", 
        "debit card" to "bad credit",
        "hello" to "world"
    )
    
    for ((str1, str2) in knownAnagrams) {
        // Different ways to call the infix function:
        
        // 1. Infix notation (recommended)
        val result1 = str1 anagramOf str2
        
        // 2. Standard method call (still works)
        val result2 = str1.anagramOf(str2)
        
        // 3. Using the alternative implementation
        val result3 = str1 anagramOfUsingSorting str2
        
        println("'$str1' anagramOf '$str2' = $result1")
        println("  (standard call: ${str1.anagramOf(str2)})")
        println("  (sorting method: ${str1 anagramOfUsingSorting str2})")
    }
    
    // Chain infix operations
    println("\n=== Chaining Infix Operations ===")
    val testString = "listen"
    val results = listOf("silent", "enlist", "hello", "world")
    
    for (other in results) {
        val isAnagram = testString anagramOf other
        val lengthMatch = testString.length == other.length
        
        // Using multiple infix operations
        if (isAnagram && lengthMatch) {
            println("'$testString' and '$other' are perfect anagrams!")
        } else if (isAnagram) {
            println("'$testString' and '$other' are anagrams (after normalization)")
        } else {
            println("'$testString' and '$other' are NOT anagrams")
        }
    }
}

/**
 * Additional infix extension function for comparing frequency maps
 */
infix fun Map<Char, Int>.anagramMapMatches(other: Map<Char, Int>): Boolean {
    return this == other
}

/**
 * Infix function to check if strings have same length after normalization
 */
infix fun String.sameNormalizedLength(other: String): Boolean {
    return this.lowercase().filter { it.isLetter() }.length == 
           other.lowercase().filter { it.isLetter() }.length
}

/**
 * Function to test with known anagrams using infix notation
 */
fun testKnownAnagramsInfix() {
    println("\n=== Testing Known Anagrams (Infix Notation) ===")
    
    val testCases = listOf(
        "listen" to "silent",
        "evil" to "vile",
        "debit card" to "bad credit",
        "eleven plus two" to "twelve plus one",
        "hello" to "world",
        "apple" to "pale",
        "Tom Marvolo Riddle" to "I am Lord Voldemort"
    )
    
    for ((str1, str2) in testCases) {
        // Using infix notation for cleaner, more readable code
        val result = str1 anagramOf str2
        println("'$str1' anagramOf '$str2' → ${if (result) "Anagrams" else "Not anagrams"}")
        
        // Also show normalized length comparison using infix
        val sameLength = str1 sameNormalizedLength str2
        println("  Same normalized length: $sameLength")
    }
}

/**
 * Interactive infix demonstration
 */
fun interactiveInfixDemo() {
    println("\n=== Interactive Infix Demo ===")
    
    while (true) {
        print("Enter first string (or 'quit'): ")
        val str1 = readln().trim()
        if (str1.equals("quit", ignoreCase = true)) break
        
        print("Enter second string: ")
        val str2 = readln().trim()
        
        // Using infix notation for the main check
        val areAnagrams = str1 anagramOf str2
        val sameLength = str1 sameNormalizedLength str2
        
        println("Result: ${if (areAnagrams) "ANAGRAMS" else "NOT ANAGRAMS"}")
        println("Same normalized length: $sameLength")
        println("-".repeat(40))
    }
}
