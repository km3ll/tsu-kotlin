package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Lazy")

    /**
     * Lazy Property
     * - Its value is computed on the first access (by lazy)
     * - 'lazy' is a function that takes a lambda as an argument
     */
    val lazyValue: String by lazy {
        println(" > computed")
        "hello"
    }
    println(" > value: $lazyValue")
    println(" > value: $lazyValue")

}