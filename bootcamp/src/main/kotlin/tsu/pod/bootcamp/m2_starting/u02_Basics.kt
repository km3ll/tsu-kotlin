package tsu.pod.bootcamp.m2_starting

/**
 * Top-Level Functions
 * - Functions defined at package level
 * - General functions without binding them to specific class
 */

/**
 * Main program arguments can be omitted
 * - fun main() {...}
 */
fun main(args: Array<String>) {
    println("Basics")

    /**
     * IF Expression
     * - You can assign the result to a variable or return it from a function
     */
    val name = if (args.isNotEmpty()) args[0] else "Pod"

    /**
     * String Templates ${}
     */
    println(" > ${greeting()}, $name!")
    println(" > ${greeting()}, ${args.getOrNull(0)}")
}

fun greeting(): String {
    println(" > Getting greeting...")
    return "Hello"
}
