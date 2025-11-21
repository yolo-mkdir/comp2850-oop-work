import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@DisplayName("Anagram Checker Tests")
class AnagramCheckerTest {
    
    @Test
    @DisplayName("Basic anagrams should return true")
    fun testBasicAnagrams() {
        assertTrue(areAnagrams("listen", "silent"))
        assertTrue(areAnagrams("abc", "cba"))
        assertTrue(areAnagrams("a", "a"))
    }
    
    @Test
    @DisplayName("Non-anagrams should return false")
    fun testNonAnagrams() {
        assertFalse(areAnagrams("hello", "world"))
        assertFalse(areAnagrams("test", "best"))
        assertFalse(areAnagrams("abc", "abcd"))
    }
    
    @Test
    @DisplayName("Anagrams with different cases should return true")
    fun testCaseInsensitiveAnagrams() {
        assertTrue(areAnagrams("Listen", "Silent"))
        assertTrue(areAnagrams("ABC", "cba"))
    }
    
    @Test
    @DisplayName("Anagrams with spaces should return true")
    fun testAnagramsWithSpaces() {
        assertTrue(areAnagrams("funeral", "real fun"))
        assertTrue(areAnagrams("school master", "the classroom"))
    }
    
    @Test
    @DisplayName("Different length strings should return false")
    fun testDifferentLengthStrings() {
        assertFalse(areAnagrams("short", "longer"))
        assertFalse(areAnagrams("a", "ab"))
    }
    
    @Test
    @DisplayName("Empty strings should be handled correctly")
    fun testEmptyStrings() {
        assertTrue(areAnagrams("", ""))
        assertFalse(areAnagrams("", "a"))
    }
}
