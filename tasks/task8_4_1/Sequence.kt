// Task 8.4.1: experimenting with sequences

/**
 * This program demonstrates the difference between regular collections
 * and sequences in Kotlin, showing lazy evaluation and performance benefits.
 */

fun main() {
    val numbers = listOf(1, 4, 7, 2, 9, 3, 8)

    println("=== Regular Collection Operations ===")
    val collectionResult = numbers
        .filter { 
            println("Filtering: $it")
            it > 3 
        }
        .map { 
            println("Mapping: $it")
            it * 2 
        }
        .take(3)
    
    println("Collection result: $collectionResult")
    
    println("\n=== Sequence Operations ===")
    val sequenceResult = numbers.asSequence()
        .filter { 
            println("Sequence filtering: $it")
            it > 3 
        }
        .map { 
            println("Sequence mapping: $it")
            it * 2 
        }
        .take(3)
        .toList()
    
    println("Sequence result: $sequenceResult")
    
    // Additional sequence demonstrations
    demonstrateMoreSequenceOperations()
    demonstratePerformanceDifference()
    demonstrateInfiniteSequences()
}

/**
 * Demonstrates more sequence operations
 */
fun demonstrateMoreSequenceOperations() {
    println("\n" + "=".repeat(50))
    println("ADDITIONAL SEQUENCE OPERATIONS")
    println("=".repeat(50))
    
    val numbers = listOf(1, 4, 7, 2, 9, 3, 8, 6, 5, 10)
    
    // 1. Basic sequence operations
    println("Original numbers: $numbers")
    
    val sequence1 = numbers.asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .toList()
    println("Even numbers squared: $sequence1")
    
    // 2. Chunked and windowed operations with sequences
    val sequence2 = numbers.asSequence()
        .windowed(3) { it.sum() }
        .toList()
    println("Sliding window sums (size 3): $sequence2")
    
    // 3. Distinct and sorted operations
    val sequence3 = numbers.asSequence()
        .distinct()
        .sorted()
        .toList()
    println("Distinct and sorted: $sequence3")
    
    // 4. FlatMap with sequences
    val sequence4 = numbers.asSequence()
        .flatMap { number -> 
            generateSequence(1) { it + 1 }
                .take(number)
                .map { "Item$it" }
        }
        .take(15)
        .toList()
    println("FlatMap result (first 15): $sequence4")
}

/**
 * Demonstrates performance difference between collections and sequences
 */
fun demonstratePerformanceDifference() {
    println("\n" + "=".repeat(50))
    println("PERFORMANCE COMPARISON")
    println("=".repeat(50))
    
    val largeList = (1..1_000_000).toList()
    
    // Collection operations (eager evaluation)
    val collectionStartTime = System.currentTimeMillis()
    val collectionResult = largeList
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(10)
    val collectionTime = System.currentTimeMillis() - collectionStartTime
    
    // Sequence operations (lazy evaluation)
    val sequenceStartTime = System.currentTimeMillis()
    val sequenceResult = largeList.asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(10)
        .toList()
    val sequenceTime = System.currentTimeMillis() - sequenceStartTime
    
    println("Collection result: $collectionResult")
    println("Collection time: ${collectionTime}ms")
    println("Sequence result: $sequenceResult")
    println("Sequence time: ${sequenceTime}ms")
    println("Performance improvement: ${(collectionTime - sequenceTime)}ms faster with sequences")
}

/**
 * Demonstrates infinite sequences
 */
fun demonstrateInfiniteSequences() {
    println("\n" + "=".repeat(50))
    println("INFINITE SEQUENCES")
    println("=".repeat(50))
    
    // 1. Infinite sequence of natural numbers
    val naturalNumbers = generateSequence(1) { it + 1 }
    println("First 10 natural numbers: ${naturalNumbers.take(10).toList()}")
    
    // 2. Infinite sequence of Fibonacci numbers
    val fibonacci = sequence {
        var a = 0
        var b = 1
        yield(a)
        yield(b)
        
        while (true) {
            val next = a + b
            yield(next)
            a = b
            b = next
        }
    }
    println("First 15 Fibonacci numbers: ${fibonacci.take(15).toList()}")
    
    // 3. Infinite sequence with condition
    val powersOfTwo = generateSequence(1) { it * 2 }
    println("Powers of 2 under 1000: ${powersOfTwo.takeWhile { it < 1000 }.toList()}")
    
    // 4. Sequence from function
    val randomNumbers = generateSequence { 
        (1..100).random() 
    }
    println("10 random numbers: ${randomNumbers.take(10).toList()}")
}

/**
 * Demonstrates sequence generation methods
 */
fun demonstrateSequenceGeneration() {
    println("\n" + "=".repeat(50))
    println("SEQUENCE GENERATION METHODS")
    println("=".repeat(50))
    
    // Method 1: asSequence() from collections
    val fromList = listOf(1, 2, 3, 4, 5).asSequence()
    println("From list: ${fromList.toList()}")
    
    // Method 2: generateSequence with seed
    val countedSequence = generateSequence(1) { if (it < 5) it + 1 else null }
    println("Counted sequence: ${countedSequence.toList()}")
    
    // Method 3: sequence builder with yield
    val builtSequence = sequence {
        yield(10)
        yieldAll(listOf(20, 30, 40))
        yieldAll(generateSequence(50) { it + 10 }.take(3))
    }
    println("Built sequence: ${builtSequence.toList()}")
    
    // Method 4: sequenceOf
    val simpleSequence = sequenceOf(100, 200, 300)
    println("Simple sequence: ${simpleSequence.toList()}")
}

/**
 * Advanced sequence operations with real-world example
 */
fun demonstrateRealWorldExample() {
    println("\n" + "=".repeat(50))
    println("REAL-WORLD EXAMPLE: DATA PROCESSING PIPELINE")
    println("=".repeat(50))
    
    data class Person(val name: String, val age: Int, val salary: Double)
    
    val people = listOf(
        Person("Alice", 25, 50000.0),
        Person("Bob", 30, 60000.0),
        Person("Charlie", 35, 70000.0),
        Person("Diana", 28, 55000.0),
        Person("Eve", 40, 80000.0),
        Person("Frank", 22, 45000.0),
        Person("Grace", 33, 65000.0),
        Person("Henry", 29, 58000.0),
        Person("Ivy", 31, 62000.0),
        Person("Jack", 26, 52000.0)
    )
    
    // Process people data using sequences
    val result = people.asSequence()
        .filter { 
            println("Filtering by age: ${it.name} (${it.age})")
            it.age >= 25 && it.age <= 35 
        }
        .map { 
            println("Calculating bonus for: ${it.name}")
            it.copy(salary = it.salary * 1.1) // 10% bonus
        }
        .sortedByDescending { it.salary }
        .take(3)
        .toList()
    
    println("\nTop 3 people aged 25-35 with 10% bonus:")
    result.forEach { person ->
        println("${person.name}: ${person.age} years, Salary: $${"%.2f".format(person.salary)}")
    }
}

// Run additional demonstrations
fun runAllDemonstrations() {
    demonstrateMoreSequenceOperations()
    demonstratePerformanceDifference()
    demonstrateInfiniteSequences()
    demonstrateSequenceGeneration()
    demonstrateRealWorldExample()
}
