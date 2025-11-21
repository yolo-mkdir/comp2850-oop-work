// Task 6.4.1: functions for handling exam grades

fun grade(mark: Int) = when (mark) {
    in 70..100 -> "Distinction"  // 修正：从70开始，不是71
    in 40..69  -> "Pass"
    in 0..39   -> "Fail"
    else       -> "?"
}

fun displayGrades(marks: Map<String,Int>) {
    if (marks.isEmpty()) {
        println("No marks available")
    }
    else {
        for ((name, mark) in marks) {
            println("$name: ${grade(mark)}")
        }
    }
}
