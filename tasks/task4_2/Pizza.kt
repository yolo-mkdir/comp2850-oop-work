/**
 * Task 4.2 - Pizza Ordering System
 * 
 * This program presents a pizza menu and validates user input.
 */

fun main() {
    println("=== Welcome to Kotlin Pizzeria ===")
    displayMenu()
    
    print("Please enter your choice (a, b, c, or d): ")
    val userInput = readln().lowercase()
    
    // Validate the input
    val result = if (userInput.length == 1 && userInput[0] in 'a'..'d') {
        "Order accepted"
    } else {
        "Invalid choice!"
    }
    
    println(result)
    
    // Optional: Display what was ordered
    if (result == "Order accepted") {
        displayOrderDetails(userInput[0])
    }
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

// Alternative implementation using when expression
fun validateInputAlternative(input: String): String {
    return when {
        input.length != 1 -> "Invalid choice!"
        input[0] in 'a'..'d' -> "Order accepted"
        else -> "Invalid choice!"
    }
}
