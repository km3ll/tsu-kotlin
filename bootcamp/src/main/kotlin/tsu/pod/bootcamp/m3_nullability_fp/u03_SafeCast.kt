package tsu.pod.bootcamp.m3_nullability_fp

fun main() {
    println("Safe Cast")

    /**
     * Type Cast
     * - is: instanceOf
     * - as: type cast
     */
    val any1: Any = "John"
    if (any1 is String) {
        val name: String = any1 as String
        println(" > name: ${name.uppercase()}")
    }

    /**
     * Safe Cast
     * as? returns null if it cannot be cast
     */
    val any2: Any = "Wick"
    val lastName = (any2 as? String)?.uppercase()
    println(" > lastName: $lastName")

}
