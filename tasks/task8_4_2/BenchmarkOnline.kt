/**
 * Task 8.4.2 - Sequence Performance Benchmark (Online Version)
 * 
 * Simplified version for online Kotlin compiler at https://play.kotlinlang.org/
 * Copy and paste this entire code to see the performance difference.
 */

import kotlin.system.measureTimeMillis

fun main() {
    println("=== Sequence Performance Benchmark ===")
    println("Online Compiler Version")
    println()
    
    // Create sample text data (simulating a large file)
    val sampleText = generateSampleText()
    val lines = sampleText.lines()
    
    println("Sample data: ${lines.size} lines, ${sampleText.length} characters")
    println()
    
    // Benchmark 1: Simple text processing
    println("BENCHMARK 1: Text Processing")
    println("-".repeat(40))
    
    val collectionTime1 = measureTimeMillis {
        val result = lines
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .filter { it.length > 10 }
            .map { it.uppercase() }
            .filter { it.contains("THE") }
            .take(50)
        println("Collections found: ${result.size} lines")
    }
    
    val sequenceTime1 = measureTimeMillis {
        val result = lines.asSequence()
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .filter { it.length > 10 }
            .map { it.uppercase() }
            .filter { it.contains("THE") }
            .take(50)
            .toList()
        println("Sequences found: ${result.size} lines")
    }
    
    println("Collections: ${collectionTime1}ms")
    println("Sequences: ${sequenceTime1}ms")
    val improvement1 = ((collectionTime1 - sequenceTime1).toDouble() / collectionTime1 * 100)
    println("Improvement: ${"%.1f".format(improvement1)}%")
    println()
    
    // Benchmark 2: Word processing
    println("BENCHMARK 2: Word Analysis")
    println("-".repeat(40))
    
    val allText = sampleText
    val collectionTime2 = measureTimeMillis {
        val words = allText.split("\\s+".toRegex())
            .filter { it.length > 4 }
            .map { it.lowercase().replace("[^a-z]".toRegex(), "") }
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
        println("Collections: ${words.size} unique words")
    }
    
    val sequenceTime2 = measureTimeMillis {
        val words = allText.splitToSequence("\\s+".toRegex())
            .filter { it.length > 4 }
            .map { it.lowercase().replace("[^a-z]".toRegex(), "") }
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
        println("Sequences: ${words.size} unique words")
    }
    
    println("Collections: ${collectionTime2}ms")
    println("Sequences: ${sequenceTime2}ms")
    val improvement2 = ((collectionTime2 - sequenceTime2).toDouble() / collectionTime2 * 100)
    println("Improvement: ${"%.1f".format(improvement2)}%")
    println()
    
    // Summary
    println("SUMMARY")
    println("-".repeat(40))
    println("Sequences are more efficient because:")
    println("• Lazy evaluation (process elements as needed)")
    println("• No intermediate collections created")
    println("• Better memory usage")
    println("• Faster for large datasets and chained operations")
}

/**
 * Generate sample text for benchmarking
 */
fun generateSampleText(): String {
    val paragraphs = listOf(
        "The quick brown fox jumps over the lazy dog. This sentence contains all the letters in the English alphabet.",
        "In the beginning, there was light and darkness. The world was without form and void.",
        "To be or not to be, that is the question. Whether it is nobler in the mind to suffer.",
        "It was the best of times, it was the worst of times. It was the age of wisdom and foolishness.",
        "Call me Ishmael. Some years ago, never mind how long precisely, I thought I would sail about.",
        "Happy families are all alike; every unhappy family is unhappy in its own way.",
        "It is a truth universally acknowledged, that a single man in possession of a good fortune.",
        "The sun did not shine. It was too wet to play. So we sat in the house all that cold, wet day.",
        "Once upon a time, there were four little rabbits. Their names were Flopsy, Mopsy, Cottontail, and Peter.",
        "The Mole had been working very hard all the morning, spring-cleaning his little home."
    )
    
    // Repeat paragraphs to create a larger dataset
    return buildString {
        repeat(100) { index ->
            append(paragraphs[index % paragraphs.size])
            append("\n\n")
        }
    }
}

/**
 * Demonstration of processing steps
 */
fun demonstrateProcessingSteps() {
    println("\nPROCESSING STEPS DEMONSTRATION")
    println("-".repeat(40))
    
    val sampleData = listOf(
        "  Hello World  ",
        "This is a test",
        "  THE important line  ",
        "",
        "Another line with THE word",
        "Short"
    )
    
    println("Input data:")
    sampleData.forEach { println("  '$it'") }
    println()
    
    println("Collections (eager evaluation):")
    println("  Processes ALL elements at each step")
    val collectionResult = sampleData
        .filter { 
            println("    Filter blank: '$it'")
            it.isNotBlank() 
        }
        .map { 
            println("    Trim: '$it'")
            it.trim() 
        }
        .filter { 
            println("    Filter length > 5: '$it'")
            it.length > 5 
        }
        .map { 
            println("    Uppercase: '$it'")
            it.uppercase() 
        }
    println("  Final result: $collectionResult")
    println()
    
    println("Sequences (lazy evaluation):")
    println("  Processes elements one-by-one through entire pipeline")
    val sequenceResult = sampleData.asSequence()
        .filter { 
            println("    Sequence filter blank: '$it'")
            it.isNotBlank() 
        }
        .map { 
            println("    Sequence trim: '$it'")
            it.trim() 
        }
        .filter { 
            println("    Sequence filter length > 5: '$it'")
            it.length > 5 
        }
        .map { 
            println("    Sequence uppercase: '$it'")
            it.uppercase() 
        }
        .toList()
    println("  Final result: $sequenceResult")
}
