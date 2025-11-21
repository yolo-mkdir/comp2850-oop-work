/**
 * Task 8.4.2 - Sequence Performance Benchmark
 * 
 * This program demonstrates the performance benefits of sequences
 * when processing large text files like War and Peace.
 */

import java.io.File
import kotlin.system.measureTimeMillis

/**
 * Main class for benchmarking sequences vs collections
 */
class TextProcessor {
    
    /**
     * Process text using collections (eager evaluation)
     */
    fun processWithCollections(lines: List<String>): List<String> {
        return lines
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .filter { it.length > 10 }
            .map { it.uppercase() }
            .filter { it.contains("THE") }
            .take(1000)
    }
    
    /**
     * Process text using sequences (lazy evaluation)
     */
    fun processWithSequences(lines: List<String>): List<String> {
        return lines.asSequence()
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .filter { it.length > 10 }
            .map { it.uppercase() }
            .filter { it.contains("THE") }
            .take(1000)
            .toList()
    }
    
    /**
     * Additional benchmark: Word frequency analysis
     */
    fun wordFrequencyWithCollections(text: String): Map<String, Int> {
        return text.split("\\s+".toRegex())
            .filter { it.length > 3 }
            .map { it.lowercase().replace("[^a-z]".toRegex(), "") }
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(50)
            .toMap()
    }
    
    fun wordFrequencyWithSequences(text: String): Map<String, Int> {
        return text.splitToSequence("\\s+".toRegex())
            .filter { it.length > 3 }
            .map { it.lowercase().replace("[^a-z]".toRegex(), "") }
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(50)
            .toMap()
    }
    
    /**
     * Benchmark: Find longest words
     */
    fun longestWordsWithCollections(lines: List<String>): List<String> {
        return lines.flatMap { it.split("\\s+".toRegex()) }
            .filter { it.length > 5 }
            .map { it.replace("[^a-zA-Z]".toRegex(), "") }
            .distinct()
            .sortedByDescending { it.length }
            .take(20)
    }
    
    fun longestWordsWithSequences(lines: List<String>): List<String> {
        return lines.asSequence()
            .flatMap { it.splitToSequence("\\s+".toRegex()) }
            .filter { it.length > 5 }
            .map { it.replace("[^a-zA-Z]".toRegex(), "") }
            .distinct()
            .sortedByDescending { it.length }
            .take(20)
            .toList()
    }
}

/**
 * Main function - entry point of the application
 */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: ./amper run <filename>")
        println("Please provide a text file name as argument")
        return
    }
    
    val filename = args[0]
    val file = File(filename)
    
    if (!file.exists()) {
        println("Error: File '$filename' not found")
        return
    }
    
    println("=== Sequence Performance Benchmark ===")
    println("Processing file: ${file.name}")
    println("File size: ${file.length()} bytes")
    
    // Read the entire file
    val lines = file.readLines()
    val fullText = file.readText()
    
    println("Total lines: ${lines.size}")
    println()
    
    val processor = TextProcessor()
    
    // Benchmark 1: Text processing pipeline
    println("BENCHMARK 1: Text Processing Pipeline")
    println("-" * 50)
    
    val collectionTime1 = measureTimeMillis {
        val result = processor.processWithCollections(lines)
        println("Collections result: ${result.size} lines found")
    }
    
    val sequenceTime1 = measureTimeMillis {
        val result = processor.processWithSequences(lines)
        println("Sequences result: ${result.size} lines found")
    }
    
    println("Collections time: ${collectionTime1}ms")
    println("Sequences time: ${sequenceTime1}ms")
    println("Performance improvement: ${((collectionTime1 - sequenceTime1).toDouble() / collectionTime1 * 100).format(1)}%")
    println()
    
    // Benchmark 2: Word frequency analysis
    println("BENCHMARK 2: Word Frequency Analysis")
    println("-" * 50)
    
    val collectionTime2 = measureTimeMillis {
        val result = processor.wordFrequencyWithCollections(fullText)
        println("Collections result: ${result.size} unique words")
        println("Top 5 words: ${result.toList().take(5).joinToString { "${it.first}(${it.second})" }}")
    }
    
    val sequenceTime2 = measureTimeMillis {
        val result = processor.wordFrequencyWithSequences(fullText)
        println("Sequences result: ${result.size} unique words")
        println("Top 5 words: ${result.toList().take(5).joinToString { "${it.first}(${it.second})" }}")
    }
    
    println("Collections time: ${collectionTime2}ms")
    println("Sequences time: ${sequenceTime2}ms")
    println("Performance improvement: ${((collectionTime2 - sequenceTime2).toDouble() / collectionTime2 * 100).format(1)}%")
    println()
    
    // Benchmark 3: Longest words
    println("BENCHMARK 3: Find Longest Words")
    println("-" * 50)
    
    val collectionTime3 = measureTimeMillis {
        val result = processor.longestWordsWithCollections(lines)
        println("Collections result: ${result.size} words")
        println("Longest 5: ${result.take(5).joinToString()}")
    }
    
    val sequenceTime3 = measureTimeMillis {
        val result = processor.longestWordsWithSequences(lines)
        println("Sequences result: ${result.size} words")
        println("Longest 5: ${result.take(5).joinToString()}")
    }
    
    println("Collections time: ${collectionTime3}ms")
    println("Sequences time: ${sequenceTime3}ms")
    println("Performance improvement: ${((collectionTime3 - sequenceTime3).toDouble() / collectionTime3 * 100).format(1)}%")
    println()
    
    // Summary
    println("SUMMARY")
    println("-" * 50)
    val totalCollectionTime = collectionTime1 + collectionTime2 + collectionTime3
    val totalSequenceTime = sequenceTime1 + sequenceTime2 + sequenceTime3
    println("Total collections time: ${totalCollectionTime}ms")
    println("Total sequences time: ${totalSequenceTime}ms")
    println("Overall improvement: ${((totalCollectionTime - totalSequenceTime).toDouble() / totalCollectionTime * 100).format(1)}%")
    
    // Memory usage comparison
    println()
    println("MEMORY EFFICIENCY")
    println("-" * 50)
    println("Collections create intermediate collections for each operation")
    println("Sequences process elements one-by-one through the entire pipeline")
    println("This makes sequences more memory-efficient for large datasets")
}

/**
 * Extension function to repeat a string
 */
operator fun String.times(n: Int): String = this.repeat(n)

/**
 * Extension function to format Double values
 */
fun Double.format(decimals: Int): String = "%.${decimals}f".format(this)

/**
 * Additional demonstration: Processing with progress tracking
 */
fun demonstrateProcessingSteps() {
    println("\nPROCESSING STEPS DEMONSTRATION")
    println("-" * 50)
    
    val sampleLines = listOf(
        "This is a short line",
        "  This line has leading spaces  ",
        "This is a longer line that contains multiple words",
        "",
        "THE quick brown fox",
        "Another line with THE word",
        "Short",
        "This line also contains THE important word"
    )
    
    println("Sample data:")
    sampleLines.forEachIndexed { index, line -> println("${index + 1}. '$line'") }
    println()
    
    println("Collections processing (eager):")
    val collectionResult = sampleLines
        .filter { 
            println("  Filtering blank: '$it' -> ${it.isNotBlank()}")
            it.isNotBlank() 
        }
        .map { 
            val trimmed = it.trim()
            println("  Trimming: '$it' -> '$trimmed'")
            trimmed
        }
        .filter { 
            println("  Filtering length > 10: '$it' -> ${it.length > 10}")
            it.length > 10 
        }
        .map { 
            val upper = it.uppercase()
            println("  Converting to uppercase: '$it' -> '$upper'")
            upper
        }
        .filter { 
            println("  Filtering contains 'THE': '$it' -> ${it.contains("THE")}")
            it.contains("THE") 
        }
    
    println("Final collection result: $collectionResult")
    println()
    
    println("Sequence processing (lazy):")
    val sequenceResult = sampleLines.asSequence()
        .filter { 
            println("  Sequence filtering blank: '$it' -> ${it.isNotBlank()}")
            it.isNotBlank() 
        }
        .map { 
            val trimmed = it.trim()
            println("  Sequence trimming: '$it' -> '$trimmed'")
            trimmed
        }
        .filter { 
            println("  Sequence filtering length > 10: '$it' -> ${it.length > 10}")
            it.length > 10 
        }
        .map { 
            val upper = it.uppercase()
            println("  Sequence converting to uppercase: '$it' -> '$upper'")
            upper
        }
        .filter { 
            println("  Sequence filtering contains 'THE': '$it' -> ${it.contains("THE")}")
            it.contains("THE") 
        }
        .toList()
    
    println("Final sequence result: $sequenceResult")
}
