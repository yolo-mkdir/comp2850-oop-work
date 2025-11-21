/**
 * Task 7.2 - Array Operations
 * 
 * This program demonstrates various array operations in Kotlin including:
 * - Array creation and initialization
 * - Accessing and modifying array elements
 * - Array iteration and traversal
 * - Common array operations and methods
 */

fun main() {
    println("=== Array Operations Demo ===")
    
    // 1. Array creation and initialization
    demonstrateArrayCreation()
    
    // 2. Array access and modification
    demonstrateArrayAccess()
    
    // 3. Array iteration
    demonstrateArrayIteration()
    
    // 4. Array operations
    demonstrateArrayOperations()
    
    // 5. Multi-dimensional arrays
    demonstrateMultiDimensionalArrays()
}

/**
 * Demonstrates different ways to create and initialize arrays
 */
fun demonstrateArrayCreation() {
    println("\n--- Array Creation ---")
    
    // Method 1: Using arrayOf()
    val numbers = arrayOf(1, 2, 3, 4, 5)
    println("Array using arrayOf(): ${numbers.contentToString()}")
    
    // Method 2: Using Array constructor with lambda
    val squares = Array(5) { i -> (i + 1) * (i + 1) }
    println("Squares array: ${squares.contentToString()}")
    
    // Method 3: Primitive type arrays
    val intArray = intArrayOf(10, 20, 30, 40, 50)
    val doubleArray = doubleArrayOf(1.1, 2.2, 3.3, 4.4, 5.5)
    val charArray = charArrayOf('A', 'B', 'C', 'D', 'E')
    val booleanArray = booleanArrayOf(true, false, true, false)
    
    println("Int array: ${intArray.contentToString()}")
    println("Double array: ${doubleArray.contentToString()}")
    println("Char array: ${charArray.contentToString()}")
    println("Boolean array: ${booleanArray.contentToString()}")
    
    // Method 4: Empty arrays
    val emptyArray = emptyArray<String>()
    println("Empty array size: ${emptyArray.size}")
}

/**
 * Demonstrates accessing and modifying array elements
 */
fun demonstrateArrayAccess() {
    println("\n--- Array Access and Modification ---")
    
    val fruits = arrayOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    println("Original fruits: ${fruits.contentToString()}")
    
    // Accessing elements
    println("First fruit: ${fruits[0]}")
    println("Last fruit: ${fruits[fruits.size - 1]}")
    println("First fruit (using first()): ${fruits.first()}")
    println("Last fruit (using last()): ${fruits.last()}")
    
    // Safe access
    println("Element at index 2: ${fruits.getOrNull(2)}")
    println("Element at index 10: ${fruits.getOrNull(10)}")
    
    // Modifying elements
    fruits[1] = "Blueberry"
    fruits.set(3, "Dragonfruit")
    println("Modified fruits: ${fruits.contentToString()}")
    
    // Array bounds checking
    try {
        println("Accessing invalid index: ${fruits[10]}")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Caught ArrayIndexOutOfBoundsException: ${e.message}")
    }
}

/**
 * Demonstrates different ways to iterate through arrays
 */
fun demonstrateArrayIteration() {
    println("\n--- Array Iteration ---")
    
    val colors = arrayOf("Red", "Green", "Blue", "Yellow", "Purple")
    
    // Method 1: Using for loop with index
    println("Using for loop with index:")
    for (i in colors.indices) {
        println("Index $i: ${colors[i]}")
    }
    
    // Method 2: Using for loop with value
    println("\nUsing for loop with value:")
    for (color in colors) {
        println("Color: $color")
    }
    
    // Method 3: Using forEach
    println("\nUsing forEach:")
    colors.forEach { color ->
        println("Color: $color")
    }
    
    // Method 4: Using forEachIndexed
    println("\nUsing forEachIndexed:")
    colors.forEachIndexed { index, color ->
        println("Index $index: $color")
    }
    
    // Method 5: Using while loop
    println("\nUsing while loop:")
    var index = 0
    while (index < colors.size) {
        println("Index $index: ${colors[index]}")
        index++
    }
}

/**
 * Demonstrates common array operations and methods
 */
fun demonstrateArrayOperations() {
    println("\n--- Array Operations ---")
    
    val numbers = arrayOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("Original numbers: ${numbers.contentToString()}")
    
    // Basic properties
    println("Array size: ${numbers.size}")
    println("Array is empty: ${numbers.isEmpty()}")
    println("Array is not empty: ${numbers.isNotEmpty()}")
    
    // Searching
    println("Contains 8: ${numbers.contains(8)}")
    println("Index of 3: ${numbers.indexOf(3)}")
    println("Last index: ${numbers.lastIndex}")
    
    // Sorting
    val sortedNumbers = numbers.sortedArray()
    println("Sorted numbers: ${sortedNumbers.contentToString()}")
    
    val descendingNumbers = numbers.sortedArrayDescending()
    println("Descending sorted: ${descendingNumbers.contentToString()}")
    
    // Filtering
    val evenNumbers = numbers.filter { it % 2 == 0 }.toTypedArray()
    println("Even numbers: ${evenNumbers.contentToString()}")
    
    // Mapping
    val doubledNumbers = numbers.map { it * 2 }.toTypedArray()
    println("Doubled numbers: ${doubledNumbers.contentToString()}")
    
    // Reduction
    val sum = numbers.sum()
    val average = numbers.average()
    println("Sum: $sum, Average: $average")
    
    // Min and Max
    println("Min: ${numbers.minOrNull()}, Max: ${numbers.maxOrNull()}")
}

/**
 * Demonstrates multi-dimensional arrays
 */
fun demonstrateMultiDimensionalArrays() {
    println("\n--- Multi-dimensional Arrays ---")
    
    // 2D array (matrix)
    val matrix = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    
    println("2D Matrix:")
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print("${matrix[i][j]} ")
        }
        println()
    }
    
    // Accessing specific elements
    println("Element at [1][2]: ${matrix[1][2]}")
    
    // 3D array
    val cube = arrayOf(
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)),
        arrayOf(intArrayOf(5, 6), intArrayOf(7, 8))
    )
    
    println("\n3D Cube:")
    for (i in cube.indices) {
        println("Layer $i:")
        for (j in cube[i].indices) {
            for (k in cube[i][j].indices) {
                print("${cube[i][j][k]} ")
            }
            println()
        }
        println()
    }
}
