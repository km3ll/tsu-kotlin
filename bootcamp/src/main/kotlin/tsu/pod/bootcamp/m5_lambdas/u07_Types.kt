package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("Types")
    /**
     * Types
     * - no primitives
     * - Any
     *   - Int
     *     - Nothing
     * - Any?
     *   - Int?
     *     - Nothing?
     *
     * - Unit (void)
     *   - no meaningful value is returned
     *   - a type that allows only one value and thus can hold no information
     *
     * - Nothing
     *   - it means this function never returns
     *   - a type that has no values (neven completes)
     */

    // Unit: completes normally
    fun ask() {
        println(" > How are you?")
    }

    // Nothing: completes abnormally
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    fun infinite(): Nothing {
        while (true) {}
    }

    val timeHasPassed = true
    val answer: Int = if (timeHasPassed) {
        42
    } else {
        TODO("Needs to me implemented")
    }

}
