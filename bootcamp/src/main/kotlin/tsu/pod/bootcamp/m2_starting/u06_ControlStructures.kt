package tsu.pod.bootcamp.m2_starting
import tsu.pod.bootcamp.m2_starting.Colore.*

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
     * - with enumerator
     * - with possible values
     * - 'is' operator (isInstanceOf)
     * - with conditions
     */
    println(" > description: ${getDescription(Status.ORANGE)}")
    println(" > response: ${respond("yes")}")
    println(" > mix: ${mix(BLUE, VIOLET)}")

    /**
     * Checking type
     * - 'is' operator (isInstanceOf)
     */
    val shape1: Shape = Circle()
    val shape2: Shape = Rectangle()
    println(" > check: ${check(shape1)}")
    println(" > check: ${check(shape2)}")

    println(" > favourite")
    when (val shape3 = getFavouriteShape()) {
        is Circle -> println(" > circle")
        else -> println(" > invalid $shape3")
    }
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

fun respond(input: String): String =
    when (input) {
        "yes" -> "continue"
        "no" -> "abort"
        else -> "error"
    }

enum class Colore {
    YELLOW,
    BLUE,
    RED,
    GREEN,
    VIOLET,
    ORANGE,
    INDIGO,
}

fun mix(
    c1: Colore,
    c2: Colore,
) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Invalid color")
}

open class Shape {
    open fun display() {
        println("Shape")
    }
}

class Circle : Shape()

class Rectangle : Shape()

fun check(shape: Shape): String =
    when (shape) {
        is Circle -> "circle"
        is Rectangle -> "rectangle"
        else -> throw Exception("Invalid shape")
    }

fun getFavouriteShape(): Shape = Circle()

fun checkWeather(degrees: Int): String =
    when {
        degrees < 5 -> "cold"
        degrees < 25 -> "mild"
        else -> "hot"
    }
