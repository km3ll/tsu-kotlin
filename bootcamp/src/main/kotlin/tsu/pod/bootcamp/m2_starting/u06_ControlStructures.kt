package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Control Structures")

    /**
     * IF
     * - An expression that returns a value
     * - No ternary operator in Kotlin
     */
    val a = 10
    val b = 20
    val max = if (a > b) a else b
    println(" > if expression: $max")

    /**
     * WHEN
     */
    println(" > description: ${getDescription(Status.ORANGE)}")
    println(" > response: ${respondToInput("yes")}")
}

enum class Status {
    BLUE,
    ORANGE,
    RED,
}

fun getDescription(status: Status): String =
    when (status) {
        Status.BLUE -> "cold"
        Status.ORANGE -> "mild"
        Status.RED -> "hot"
    }

fun respondToInput(input: String): String =
    when (input) {
        "yes" -> "continue"
        "no" -> "abort"
        else -> "error"
    }
