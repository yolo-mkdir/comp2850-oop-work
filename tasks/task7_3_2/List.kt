/**
 * Task 7.3.2 - Mutable List Operations
 * 
 * Modified from Task 7.3.1 to use mutable lists instead of immutable lists.
 * This demonstrates list modification operations that are only supported for mutable lists.
 */

fun main() {
    println("=== Mutable List Operations Demo ===")
    
    // 1. List creation and initialization - using mutable lists
    demonstrateListCreation()
    
    // 2. List access and modification - focusing on mutable operations
    demonstrateListAccess()
    
    // 3. List iteration
    demonstrateListIteration()
    
    // 4. List operations
    demonstrateListOperations()
    
    // 5. Mutable list operations - expanded demonstration
    demonstrateMutableListOperations()
    
    // 6. Additional mutable operations
    demonstrateAdditionalMutableOperations()
}

/**
 * Demonstrates creating mutable lists instead of immutable lists
 */
fun demonstrateListCreation() {
    println("\n--- Mutable List Creation ---")
    
    // CHANGED: Using mutableListOf() instead of listOf() for all lists
    val fruits = mutableListOf("Apple", "Banana", "Cherry")
    println("Mutable fruits list: $fruits")
    
    val colors = mutableListOf("Red", "Green", "Blue")
    println("Mutable colors list: $colors")
    
    // Using MutableList constructor with lambda
    val squares = MutableList(5) { i -> (i + 1) * (i + 1) }
    println("Mutable squares list: $squares")
    
    // Empty mutable lists
    val emptyMutableList = mutableListOf<String>()
    println("Empty mutable list: $emptyMutableList")
    
    // Mutable lists with specific types
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    println("Mutable numbers list: $numbers")
}

/**
 * Demonstrates accessing and modifying mutable list elements
 */
fun demonstrateListAccess() {
    println("\n--- Mutable List Access and Modification ---")
    
    val fruits = mutableListOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    println("Original fruits: $fruits")
    
    // Accessing elements (same as immutable)
    println("First fruit: ${fruits[0]}")
    println("Last fruit: ${fruits[fruits.size - 1]}")
    
    // MODIFICATION: These operations only work with mutable lists
    fruits[1] = "Blueberry"  // Direct assignment
    println("After fruits[1] = 'Blueberry': $fruits")
    
    fruits.set(3, "Dragonfruit")  // Using set method
    println("After fruits.set(3, 'Dragonfruit'): $fruits")
    
    // These would cause compiler errors with immutable lists:
    // val immutableFruits = listOf("Apple", "Banana")
    // immutableFruits[0] = "Apricot"  // COMPILER ERROR!
    // immutableFruits.set(1, "Berry")  // COMPILER ERROR!
}

/**
 * Demonstrates iteration (same as immutable lists)
 */
fun demonstrateListIteration() {
    println("\n--- List Iteration ---")
    
    val colors = mutableListOf("Red", "Green", "Blue", "Yellow", "Purple")
    
    println("Using for loop with value:")
    for (color in colors) {
        println("Color: $color")
    }
    
    println("\nUsing forEach:")
    colors.forEach { color ->
        println("Color: $color")
    }
}

/**
 * Demonstrates common list operations
 */
fun demonstrateListOperations() {
    println("\n--- List Operations ---")
    
    val numbers = mutableListOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("Original numbers: $numbers")
    
    // Basic operations (same as immutable)
    println("List size: ${numbers.size}")
    println("Contains 8: ${numbers.contains(8)}")
    
    // Filtering and mapping return new lists
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even numbers (new list): $evenNumbers")
    
    val doubledNumbers = numbers.map { it * 2 }
    println("Doubled numbers (new list): $doubledNumbers")
}

/**
 * Demonstrates mutable list-specific operations
 */
fun demonstrateMutableListOperations() {
    println("\n--- Mutable List Specific Operations ---")
    
    val items = mutableListOf("Book", "Pen", "Notebook")
    println("Original items: $items")
    
    // ADDING ELEMENTS - Only available for mutable lists
    items.add("Pencil")
    println("After add(\"Pencil\"): $items")
    
    items.add(1, "Eraser")  // Insert at specific position
    println("After add(1, \"Eraser\"): $items")
    
    items.addAll(listOf("Ruler", "Stapler"))  // Add multiple items
    println("After addAll([\"Ruler\", \"Stapler\"]): $items")
    
    items.addAll(2, listOf("Highlighter", "Marker"))  // Insert multiple at position
    println("After addAll(2, [\"Highlighter\", \"Marker\"]): $items")
    
    // REMOVING ELEMENTS - Only available for mutable lists
    items.remove("Pen")
    println("After remove(\"Pen\"): $items")
    
    items.removeAt(0)  // Remove by index
    println("After removeAt(0): $items")
    
    items.removeAll(listOf("Ruler", "Stapler"))  // Remove multiple
    println("After removeAll([\"Ruler\", \"Stapler\"]): $items")
    
    items.retainAll(listOf("Eraser", "Notebook", "Highlighter"))  // Keep only these
    println("After retainAll([\"Eraser\", \"Notebook\", \"Highlighter\"]): $items")
    
    // CLEARING - Only available for mutable lists
    items.clear()
    println("After clear(): $items")
    
    // Rebuild the list for further demonstrations
    items.addAll(listOf("Computer", "Mouse", "Keyboard", "Monitor", "Printer"))
    println("Rebuilt list: $items")
}

/**
 * Demonstrates additional mutable operations
 */
fun demonstrateAdditionalMutableOperations() {
    println("\n--- Additional Mutable Operations ---")
    
    val numbers = mutableListOf(3, 1, 4, 1, 5, 9, 2, 6)
    println("Original numbers: $numbers")
    
    // SORTING IN-PLACE - Only available for mutable lists
    numbers.sort()
    println("After sort(): $numbers")
    
    numbers.sortDescending()
    println("After sortDescending(): $numbers")
    
    numbers.shuffle()
    println("After shuffle(): $numbers")
    
    numbers.reverse()
    println("After reverse(): $numbers")
    
    // FILL OPERATIONS - Only available for mutable lists
    numbers.fill(0)
    println("After fill(0): $numbers")
    
    // REPLACEMENT OPERATIONS
    numbers.clear()
    numbers.addAll(listOf(1, 2, 3, 4, 5, 6, 7, 8))
    println("New numbers: $numbers")
    
    numbers.replaceAll { it * it }  // Replace each element with its square
    println("After replaceAll { it * it }: $numbers")
    
    // REMOVE WITH CONDITION
    numbers.removeIf { it > 20 }  // Remove all elements greater than 20
    println("After removeIf { it > 20 }: $numbers")
    
    // SWAP OPERATIONS (custom implementation)
    if (numbers.size >= 2) {
        val temp = numbers[0]
        numbers[0] = numbers[1]
        numbers[1] = temp
        println("After swapping first two elements: $numbers")
    }
}

/**
 * Demonstrates the compiler errors that would occur with immutable lists
 */
fun demonstrateCompilerErrors() {
    println("\n--- Compiler Error Demonstration ---")
    
    // This code would cause compiler errors with immutable lists:
    val immutableList = listOf("A", "B", "C")
    
    println("Immutable list: $immutableList")
    println("Trying to modify immutable list would cause:")
    println("  - immutableList[0] = 'X'  → COMPILER ERROR")
    println("  - immutableList.add('D')  → COMPILER ERROR") 
    println("  - immutableList.remove('A') → COMPILER ERROR")
    println("  - immutableList.clear()   → COMPILER ERROR")
    
    // But we can read from immutable lists
    println("Reading from immutable list is fine:")
    println("  First element: ${immutableList[0]}")
    println("  Size: ${immutableList.size}")
}
