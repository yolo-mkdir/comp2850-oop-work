/**
 * Task 5.1.1 - Anagram Detector
 * 
 * This program checks if two strings are anagrams of each other.
 */

fun main() {
    println("=== Anagram Detector ===")
    
    // Read first string from user
    print("Enter the first string: ")
    val string1 = readln().trim()
    
    // Read second string from user  
    print("Enter the second string: ")
    val string2 = readln().trim()
    
    // Check if the strings are anagrams
    val result = anagrams(string1, string2)
    
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
}

/**
 * Checks if two strings are anagrams of each other.
 * Anagrams are words or phrases that contain the same characters
 * in the same frequencies, ignoring case, spaces, and punctuation.
 */
fun anagrams(s1: String, s2: String): Boolean {
    // Normalize the strings: convert to lowercase and remove non-letter characters
    val normalized1 = s1.lowercase().filter { it.isLetter() }
    val normalized2 = s2.lowercase().filter { it.isLetter() }
    
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
 * Alternative implementation using sorting
 */
fun anagramsUsingSorting(s1: String, s2: String): Boolean {
    val normalized1 = s1.lowercase().filter { it.isLetter() }.toCharArray().sorted()
    val normalized2 = s2.lowercase().filter { it.isLetter() }.toCharArray().sorted()
    
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
    
    // Check if frequencies match
    if (freq1 == freq2) {
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
 * Function to test with known anagrams
 */
fun testKnownAnagrams() {
    println("\n=== Testing Known Anagrams ===")
    
    val testCases = listOf(
        Pair("listen", "silent"),
        Pair("evil", "vile"),
        Pair("debit card", "bad credit"),
        Pair("eleven plus two", "twelve plus one"),
        Pair("hello", "world"),
        Pair("apple", "pale"),
        Pair("Tom Marvolo Riddle", "I am Lord Voldemort")
    )
    
    for ((str1, str2) in testCases) {
        val result = anagrams(str1, str2)
        println("'$str1' vs '$str2' → ${if (result) "Anagrams" else "Not anagrams"}")
    }
}
