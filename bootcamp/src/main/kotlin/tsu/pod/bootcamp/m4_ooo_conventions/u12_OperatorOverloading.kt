package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Operator Overloading")

    val pointA = Point(x = 10, y = 10)
    val pointB = Point(x = 20, y = 20)
    val pointC = pointA + pointB

    println(" > a: $pointA")
    println(" > b: $pointB")
    println(" > c: $pointC")

    /**
     * Arithmetic Operations
     * - plus
     * - minus
     * - times
     * - div
     * - mod
     */

    /**
     * Unary Operations
     * - unaryPlus (+a)
     * - unaryMinus (-a)
     * - not (!a)
     * - inc (++a, a++)
     * - dec (--a, a--)
     */

}

data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}