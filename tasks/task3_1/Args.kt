// Task 3.1: Command Line Arguments

fun main(args: Array<String>) {
    println("=== Task 3.1 - Command Line Arguments ===")
    
    // Display information about the arguments
    println("Number of arguments: ${args.size}")
    println("Arguments received: ${args.joinToString(prefix = "[", postfix = "]")}")
    
    when (args.size) {
        0 -> handleNoArguments()
        1 -> handleSingleArgument(args[0])
        2 -> handleTwoArguments(args[0], args[1])
        else -> handleMultipleArguments(args)
    }
}

fun handleNoArguments() {
    println("\nNo arguments provided.")
    println("Usage examples:")
    println("  kotlin ArgsKt")
    println("  kotlin ArgsKt arg1 arg2")
    println("  kotlin ArgsKt 'arg1 arg2'")
}

fun handleSingleArgument(arg: String) {
    println("\nSingle argument provided:")
    println("  Argument: '$arg'")
    println("  Length: ${arg.length}")
}

fun handleTwoArguments(arg1: String, arg2: String) {
    println("\nTwo arguments provided:")
    println("  Argument 1: '$arg1' (length: ${arg1.length})")
    println("  Argument 2: '$arg2' (length: ${arg2.length})")
    
    // Compare arguments
    if (arg1 == arg2) {
        println("  The arguments are identical")
    } else {
        println("  The arguments are different")
    }
}

fun handleMultipleArguments(args: Array<String>) {
    println("\nMultiple arguments provided (${args.size} total):")
    
    args.forEachIndexed { index, arg ->
        println("  Argument ${index + 1}: '$arg' (length: ${arg.length})")
    }
}
