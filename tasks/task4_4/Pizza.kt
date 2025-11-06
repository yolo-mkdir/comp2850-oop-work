/**
 * Task 4.4 - Enhanced Pizza Ordering System
 * 
 * This program presents a pizza menu and repeatedly prompts for input
 * until a valid option is provided.
 */

fun main() {
    println("=== Welcome to Kotlin Pizzeria ===")
    
    var userInput: String
    var isValidInput = false
    
    // Use a do-while loop to repeatedly prompt until valid input
    do {
        displayMenu()
        print("Please enter your choice (a, b, c, or d): ")
        userInput = readln().lowercase()
        
        // Validate the input
        isValidInput = userInput.length == 1 && userInput[0] in 'a'..'d'
        
        if (!isValidInput) {
            println("Invalid choice! Please try again.\n")
        }
    } while (!isValidInput)
    
    // Process the valid input
    println("Order accepted")
    displayOrderDetails(userInput[0])
}

fun displayMenu() {
    println("\nOur Pizza Menu:")
    println("a) Margherita - Classic tomato and mozzarella")
    println("b) Pepperoni - Tomato, mozzarella, and pepperoni")
    println("c) Vegetarian - Tomato, mozzarella, and fresh vegetables")
    println("d) Hawaiian - Tomato, mozzarella, ham, and pineapple")
    println()
}

fun displayOrderDetails(choice: Char) {
    val pizzaName = when (choice) {
        'a' -> "Margherita"
        'b' -> "Pepperoni"
        'c' -> "Vegetarian"
        'd' -> "Hawaiian"
        else -> "Unknown"
    }
    println("You ordered: $pizzaName pizza")
    println("Thank you for your order!")
}

// Alternative implementation using while loop (fixed version)
fun alternativeImplementation() {
    println("=== Welcome to Kotlin Pizzeria (Alternative) ===")
    
    var userInput: String = ""  // Initialize with empty string
    var isValidInput = false
    
    // Using while loop instead of do-while
    while (!isValidInput) {
        displayMenu()
        print("Please enter your choice (a, b, c, or d): ")
        userInput = readln().lowercase()
        
        isValidInput = userInput.length == 1 && userInput[0] in 'a'..'d'
        
        if (!isValidInput) {
            println("Invalid choice! Please try again.\n")
        }
    }
    
    println("Order accepted")
    displayOrderDetails(userInput[0])
}
