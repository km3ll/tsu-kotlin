package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Objects")

    /**
     * Object
     * - Singleton in Kotlin
     */
    KSingleton.greet()

    /**
     * Object expressions
     * - replace Java's anonymous classes
     */

    /**
     * Companion Object
     * - a nested object inside a class
     * - its members can be accessed by the class name
     */
    Controller.greet()
    Controller.staticGreet()

}

object KSingleton {
    fun greet() {
        println(" > singleton")
    }
}

class Controller {
    companion object {
        fun greet() {
            println(" > companion")
        }

        @JvmStatic
        fun staticGreet() {
            println(" > static")
        }
    }
}