import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContain
import java.io.File

/**
 * Unit tests for Wordle game functions
 * Tests cover: isValid, readWordList, pickRandomWord, and evaluateGuess
 * Uses Kotest framework for testing
 */
class WordleTest : StringSpec({
    
    // ==================== Tests for isValid() ====================
    
    "isValid should return true for a 5-letter word" {
        isValid("apple") shouldBe true
        isValid("hello") shouldBe true
        isValid("world") shouldBe true
    }
    
    "isValid should return false for words with less than 5 letters" {
        isValid("cat") shouldBe false
        isValid("hi") shouldBe false
        isValid("a") shouldBe false
        isValid("") shouldBe false
    }
    
    "isValid should return false for words with more than 5 letters" {
        isValid("banana") shouldBe false
        isValid("elephant") shouldBe false
        isValid("programming") shouldBe false
    }
    
    "isValid should work with uppercase letters" {
        isValid("APPLE") shouldBe true
        isValid("HeLLo") shouldBe true
    }
    
    "isValid should work with mixed case" {
        isValid("WoRdS") shouldBe true
    }
    
    // ==================== Tests for readWordList() ====================
    
    "readWordList should read all words from a valid file" {
        // Create a temporary test file
        val testFile = File("test_words.txt")
        testFile.writeText("apple\nworld\nhello\npeach\ngrape")
        
        val words = readWordList("test_words.txt")
        
        words shouldHaveSize 5
        words shouldContain "apple"
        words shouldContain "world"
        words shouldContain "hello"
        words shouldContain "peach"
        words shouldContain "grape"
        
        // Clean up
        testFile.delete()
    }
    
    "readWordList should handle files with empty lines" {
        val testFile = File("test_empty_lines.txt")
        testFile.writeText("apple\n\nworld\n\nhello")
        
        val words = readWordList("test_empty_lines.txt")
        
        words shouldHaveSize 3
        words shouldContain "apple"
        words shouldContain "world"
        words shouldContain "hello"
        
        testFile.delete()
    }
    
    "readWordList should handle files with whitespace" {
        val testFile = File("test_whitespace.txt")
        testFile.writeText("  apple  \n world \n\thello\t")
        
        val words = readWordList("test_whitespace.txt")
        
        words shouldHaveSize 3
        words shouldContain "apple"
        words shouldContain "world"
        words shouldContain "hello"
        
        testFile.delete()
    }
    
    "readWordList should return empty list for empty file" {
        val testFile = File("test_empty.txt")
        testFile.writeText("")
        
        val words = readWordList("test_empty.txt")
        
        words shouldHaveSize 0
        
        testFile.delete()
    }
    
    // ==================== Tests for pickRandomWord() ====================
    
    "pickRandomWord should return a word from the list" {
        val words = mutableListOf("apple", "world", "hello", "peach", "grape")
        val originalWords = words.toList()
        
        val picked = pickRandomWord(words)
        
        originalWords shouldContain picked
    }
    
    "pickRandomWord should remove the picked word from the list" {
        val words = mutableListOf("apple", "world", "hello")
        val originalSize = words.size
        
        val picked = pickRandomWord(words)
        
        words shouldHaveSize (originalSize - 1)
        words shouldNotContain picked
    }
    
    "pickRandomWord should work correctly with single word list" {
        val words = mutableListOf("apple")
        
        val picked = pickRandomWord(words)
        
        picked shouldBe "apple"
        words shouldHaveSize 0
    }
    
    "pickRandomWord should handle multiple picks" {
        val words = mutableListOf("apple", "world", "hello", "peach", "grape")
        val pickedWords = mutableListOf<String>()
        
        // Pick 3 words
        repeat(3) {
            pickedWords.add(pickRandomWord(words))
        }
        
        words shouldHaveSize 2
        pickedWords shouldHaveSize 3
        
        // All picked words should be unique
        pickedWords.toSet().size shouldBe 3
    }
    
    // ==================== Tests for evaluateGuess() ====================
    
    "evaluateGuess should return all 2s for exact match" {
        val result = evaluateGuess("apple", "apple")
        result shouldBe listOf(2, 2, 2, 2, 2)
    }
    
    "evaluateGuess should return all 0s when no letters match" {
        val result = evaluateGuess("abcde", "fghij")
        result shouldBe listOf(0, 0, 0, 0, 0)
    }
    
    "evaluateGuess should return 2 for correct position" {
        val result = evaluateGuess("axxxx", "axxxx")
        result shouldBe listOf(2, 2, 2, 2, 2)
    }
    
    "evaluateGuess should return 1 for correct letter, wrong position" {
        val result = evaluateGuess("axxxx", "xxxxa")
        result[0] shouldBe 1
        result[4] shouldBe 1
    }
    
    "evaluateGuess should handle mixed results" {
        // guess: "arose", target: "house"
        // a - not in target (0)
        // r - not in target (0)
        // o - correct position (2)
        // s - correct position (2)
        // e - correct position (2)
        val result = evaluateGuess("arose", "house")
        result shouldBe listOf(0, 0, 2, 2, 2)
    }
    
    "evaluateGuess should prioritize exact matches over wrong positions" {
        // guess: "speed", target: "erase"
        // s - in target but wrong position (1)
        // p - not in target (0)
        // e - in target, wrong position (1) 
        // e - in target, wrong position (1)
        // d - not in target (0)
        val result = evaluateGuess("speed", "erase")
        result shouldBe listOf(1, 0, 1, 1, 0)
    }
    
    "evaluateGuess should handle duplicate letters correctly" {
        // guess: "geese", target: "those"
        // g - not in target (0)
        // e - correct position (2)
        // e - in target but wrong position (1)
        // s - correct position (2)
        // e - not enough 'e's in target (0)
        val result = evaluateGuess("geese", "those")
        result shouldBe listOf(0, 2, 1, 2, 0)
    }
    
    "evaluateGuess should handle case where guess has duplicate but target has single" {
        // guess: "mummy", target: "mount"
        // m - correct position (2)
        // u - correct position (2)
        // m - not in target anymore, already used (0)
        // m - not in target (0)
        // y - not in target (0)
        val result = evaluateGuess("mummy", "mount")
        result shouldBe listOf(2, 2, 0, 0, 0)
    }
    
    "evaluateGuess should work with real Wordle examples" {
        // Example 1: guess "stare", target "slate"
        var result = evaluateGuess("stare", "slate")
        result shouldBe listOf(2, 1, 2, 0, 2)
        
        // Example 2: guess "crane", target "slate"
        result = evaluateGuess("crane", "slate")
        result shouldBe listOf(0, 0, 1, 0, 2)
        
        // Example 3: guess "weary", target "slate"
        result = evaluateGuess("weary", "slate")
        result shouldBe listOf(0, 1, 1, 0, 0)
    }
    
    "evaluateGuess should handle all same letters" {
        // guess: "aaaaa", target: "baaab"
        val result = evaluateGuess("aaaaa", "baaab")
        // a at position 0: not correct position but in target (1)
        // a at position 1: correct position (2)
        // a at position 2: correct position (2)
        // a at position 3: correct position (2)
        // a at position 4: not in target (only 4 'a's total) (0)
        result shouldBe listOf(1, 2, 2, 2, 0)
    }
    
    "evaluateGuess should handle complex duplicate scenarios" {
        // guess: "abase", target: "abbey"
        // a - correct position (2)
        // b - correct position (2)
        // a - not in remaining positions (0)
        // s - not in target (0)
        // e - wrong position (1)
        val result = evaluateGuess("abase", "abbey")
        result shouldBe listOf(2, 2, 0, 0, 1)
    }
})
