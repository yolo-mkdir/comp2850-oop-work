import java.io.File

/**
 * Validates whether a word is valid for Wordle
 * A valid word must be exactly 5 letters long
 * 
 * @param word The word to validate
 * @return true if the word is exactly 5 letters, false otherwise
 */
fun isValid(word: String): Boolean {
    return word.length == 5
}

/**
 * Reads a list of Wordle target words from a specified file
 * Each line in the file should contain one 5-letter word
 * 
 * @param filename The path to the file containing words
 * @return A mutable list of strings containing all words from the file
 */
fun readWordList(filename: String): MutableList<String> {
    val words = mutableListOf<String>()
    val file = File(filename)
    
    // Read each line from the file and add to the list
    file.forEachLine { line ->
        val trimmedLine = line.trim()
        if (trimmedLine.isNotEmpty()) {
            words.add(trimmedLine)
        }
    }
    
    return words
}

/**
 * Picks a random word from the given list, removes it from the list, and returns it
 * This prevents the same word from being chosen again in subsequent games
 * 
 * @param words A mutable list of words to choose from
 * @return A randomly selected word (which has been removed from the list)
 */
fun pickRandomWord(words: MutableList<String>): String {
    // Get a random index from the list
    val randomIndex = words.indices.random()
    
    // Remove and return the word at that index
    return words.removeAt(randomIndex)
}

/**
 * Prompts the user to enter a guess for the given attempt number
 * Validates that the guess is exactly 5 letters
 * Continues prompting until a valid guess is entered
 * 
 * @param attempt The current attempt number (for display purposes)
 * @return A valid 5-letter guess entered by the user
 */
fun obtainGuess(attempt: Int): String {
    var guess: String
    
    // Keep prompting until a valid guess is entered
    while (true) {
        print("Attempt $attempt: ")
        guess = readLine()?.trim()?.lowercase() ?: ""
        
        // Check if the guess is valid (exactly 5 letters)
        if (isValid(guess)) {
            return guess
        } else {
            println("Invalid guess. Please enter a 5-letter word.")
        }
    }
}

/**
 * Evaluates a guess against the target word using full Wordle rules
 * Returns a list of integers representing the match status for each letter:
 * - 0: Letter is not in the target word
 * - 1: Letter is in the target word but in the wrong position
 * - 2: Letter is in the correct position
 * 
 * This implementation follows the official Wordle rules where each letter in the target
 * can only "satisfy" one guess letter, prioritizing exact matches
 * 
 * @param guess The user's guess (must be 5 letters)
 * @param target The target word (must be 5 letters)
 * @return A list of 5 integers (0, 1, or 2) indicating the match status
 */
fun evaluateGuess(guess: String, target: String): List<Int> {
    val result = MutableList(5) { 0 }
    val targetLetters = target.toMutableList()
    
    // First pass: Mark all exact matches (position and letter correct)
    for (i in 0 until 5) {
        if (guess[i] == target[i]) {
            result[i] = 2
            targetLetters[i] = '-' // Mark this letter as used
        }
    }
    
    // Second pass: Check for letters in wrong positions
    for (i in 0 until 5) {
        if (result[i] == 0) { // Only check letters that aren't exact matches
            val guessLetter = guess[i]
            val indexInTarget = targetLetters.indexOf(guessLetter)
            
            if (indexInTarget != -1) {
                result[i] = 1
                targetLetters[indexInTarget] = '-' // Mark this letter as used
            }
        }
    }
    
    return result
}

/**
 * Displays a guess with visual feedback based on match results
 * Uses ANSI color codes to show:
 * - Green background for correct position (match value 2)
 * - Yellow background for wrong position (match value 1)
 * - No color for letters not in target (match value 0)
 * Falls back to text symbols if colors aren't available
 * 
 * @param guess The user's guess to display
 * @param matches A list of match values (0, 1, or 2) for each letter
 */
fun displayGuess(guess: String, matches: List<Int>) {
    // ANSI color codes for terminal output
    val greenBg = "\u001B[42m\u001B[30m" // Green background with black text
    val yellowBg = "\u001B[43m\u001B[30m" // Yellow background with black text
    val reset = "\u001B[0m" // Reset to default
    
    // Build the colored output string
    val displayString = StringBuilder()
    
    for (i in guess.indices) {
        when (matches[i]) {
            2 -> displayString.append("$greenBg ${guess[i].uppercase()} $reset")
            1 -> displayString.append("$yellowBg ${guess[i].uppercase()} $reset")
            else -> displayString.append(" ${guess[i].uppercase()} ")
        }
    }
    
    println(displayString.toString())
}
