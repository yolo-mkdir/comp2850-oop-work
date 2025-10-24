/**
 * Main program for playing a simplified version of Wordle
 * 
 * This program:
 * - Reads a list of 5-letter words from data/words.txt
 * - Picks a random target word
 * - Allows the player up to 6 attempts to guess the word
 * - Provides visual feedback using colors (green for correct position, yellow for wrong position)
 * - Displays appropriate success or failure messages
 * 
 * To run: ./gradlew run
 */
fun main() {
    println("=" .repeat(50))
    println("Welcome to Wordle!")
    println("=" .repeat(50))
    println()
    
    // Read the word list from file
    val wordListFile = "data/words.txt"
    val words: MutableList<String>
    
    try {
        words = readWordList(wordListFile)
        
        if (words.isEmpty()) {
            println("Error: The word list is empty. Please add words to $wordListFile")
            return
        }
        
        println("Loaded ${words.size} words from the dictionary.")
    } catch (e: Exception) {
        println("Error: Could not read word list from $wordListFile")
        println("Please ensure the file exists and is readable.")
        return
    }
    
    // Pick a random target word
    val target = pickRandomWord(words).lowercase()
    
    // Game instructions
    println()
    println("Guess the 5-letter word!")
    println("You have 6 attempts.")
    println()
    println("Color coding:")
    println("  \u001B[42m\u001B[30m GREEN \u001B[0m = Correct letter in correct position")
    println("  \u001B[43m\u001B[30m YELLOW \u001B[0m = Correct letter in wrong position")
    println("  Gray = Letter not in word")
    println()
    
    // Maximum number of attempts (enhanced from 10 to 6)
    val maxAttempts = 6
    var attempts = 0
    var won = false
    
    // Main game loop
    while (attempts < maxAttempts && !won) {
        attempts++
        
        // Get a valid guess from the user
        val guess = obtainGuess(attempts).lowercase()
        
        // Evaluate the guess
        val matches = evaluateGuess(guess, target)
        
        // Display the guess with visual feedback
        displayGuess(guess, matches)
        
        // Check if the player won
        if (matches.all { it == 2 }) {
            won = true
        }
        
        println()
    }
    
    // Display final message
    println("=" .repeat(50))
    if (won) {
        println("Congratulations! You guessed the word in $attempts attempt${if (attempts == 1) "" else "s"}!")
    } else {
        println("Game Over! You ran out of guesses.")
        println("The word was: ${target.uppercase()}")
    }
    println("=" .repeat(50))
}
