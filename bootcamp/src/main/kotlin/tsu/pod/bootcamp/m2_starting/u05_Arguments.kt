// @file:JvmName("Functions")
package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Arguments")

    val letters = listOf('a', 'b', 'c')
    val joined = letters.joinToString(separator = "", prefix = "[", postfix = "]")
    println(" > joined: $joined")

    /**
     * Default values
     * - char: *
     * - size: 5
     */
    display(char = '-', size = 3)
    display(char = '_')
    display(size = 4)
    display()
}

/**
 * @JvmOverloads
 * Allows to call a Kotlin function without specifying all its arguments
 */
fun display(
    char: Char = '*',
    size: Int = 5,
) {
    println(" > repeat:")
    repeat(size) {
        println("    $char")
    }
}
