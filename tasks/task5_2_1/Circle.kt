/**
 * Task 5.2.1 - Circle Calculator
 * 
 * This program calculates the area and perimeter of a circle given its radius.
 */

import kotlin.math.PI

fun main() {
    println("=== Circle Calculator ===")
    
    // Read a value for circle radius from the user
    val radius = readDouble("Enter the circle radius: ")
    
    // Validate the radius
    if (radius < 0) {
        println("Error: Radius cannot be negative.")
        return
    }
    
    if (radius == 0.0) {
        println("Warning: Radius is zero. Circle has no area or perimeter.")
    }
    
    // Compute the area & perimeter of a circle with this radius
    val area = circleArea(radius)
    val perimeter = circlePerimeter(radius)
    
    // Display area & perimeter, with four decimal places of accuracy
    println("\nResults for circle with radius $radius:")
    println("Area: ${"%.4f".format(area)}")
    println("Perimeter: ${"%.4f".format(perimeter)}")
    
    // Additional information
    displayCircleInfo(radius, area, perimeter)
}

/**
 * Calculates the area of a circle.
 * 
 * @param radius The radius of the circle
 * @return The area of the circle
 */
fun circleArea(radius: Double): Double = PI * radius * radius

/**
 * Calculates the perimeter (circumference) of a circle.
 * 
 * @param radius The radius of the circle
 * @return The perimeter of the circle
 */
fun circlePerimeter(radius: Double): Double = 2 * PI * radius

/**
 * Reads a double value from the user with a given prompt.
 * 
 * @param prompt The message to display to the user
 * @return The double value entered by the user
 */
fun readDouble(prompt: String): Double {
    print(prompt)
    val input = readln().trim()
    
    return try {
        input.toDouble()
    } catch (e: NumberFormatException) {
        println("Error: '$input' is not a valid number. Using default value 1.0.")
        1.0  // Default radius
    }
}

/**
 * Enhanced version with validation
 */
fun readDoubleWithValidation(prompt: String, min: Double = 0.0, max: Double = 1000.0): Double {
    while (true) {
        print(prompt)
        val input = readln().trim()
        
        try {
            val value = input.toDouble()
            if (value in min..max) {
                return value
            } else {
                println("Error: Please enter a number between $min and $max.")
            }
        } catch (e: NumberFormatException) {
            println("Error: '$input' is not a valid number. Please try again.")
        }
    }
}

/**
 * Displays additional circle information
 */
fun displayCircleInfo(radius: Double, area: Double, perimeter: Double) {
    println("\n--- Additional Information ---")
    println("Diameter: ${"%.4f".format(2 * radius)}")
    println("Radius: ${"%.4f".format(radius)}")
    
    // Compare with common circle sizes
    val unitCircleArea = circleArea(1.0)
    val unitCirclePerimeter = circlePerimeter(1.0)
    
    println("\nComparison with unit circle (radius = 1):")
    println("Area ratio: ${"%.4f".format(area / unitCircleArea)}")
    println("Perimeter ratio: ${"%.4f".format(perimeter / unitCirclePerimeter)}")
    
    // Show formula details
    println("\nFormulas used:")
    println("Area = π × r² = ${PI} × ${"%.2f".format(radius)}²")
    println("Perimeter = 2 × π × r = 2 × ${PI} × ${"%.2f".format(radius)}")
}

/**
 * Function to demonstrate with common circle sizes
 */
fun demonstrateCommonCircles() {
    println("\n=== Common Circle Sizes ===")
    
    val commonRadii = listOf(0.5, 1.0, 2.0, 5.0, 10.0, 100.0)
    
    for (radius in commonRadii) {
        val area = circleArea(radius)
        val perimeter = circlePerimeter(radius)
        println("Radius: ${"%.1f".format(radius)} -> Area: ${"%.4f".format(area)}, Perimeter: ${"%.4f".format(perimeter)}")
    }
}

/**
 * Alternative perimeter calculation using diameter
 */
fun circlePerimeterWithDiameter(diameter: Double): Double = PI * diameter

/**
 * Function to calculate sector area
 */
fun circleSectorArea(radius: Double, angleDegrees: Double): Double {
    return (angleDegrees / 360) * circleArea(radius)
}

/**
 * Function to calculate arc length
 */
fun circleArcLength(radius: Double, angleDegrees: Double): Double {
    return (angleDegrees / 360) * circlePerimeter(radius)
}
