// Task 8.4.1: Simplified version for online compiler

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
    
    // Show the key difference
    println("\n=== Key Difference ===")
    println("Collections process ALL elements before taking 3")
    println("Sequences process elements UNTIL 3 results are found")
}
