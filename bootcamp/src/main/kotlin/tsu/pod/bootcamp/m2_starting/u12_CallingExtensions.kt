package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Calling Extensions")

    /**
     * Extensions
     * - Under the hood are Java static functions
     * - No override for extension functions in Kotlin
     * - If you try to define an extension with the same signature
     *   as a member, then you get a warning that an extension is
     *   shadowed so the member will always be chosen instead
     */
    val instance: Parent = Child()
    println(" > greet: ${instance.greet()}")
}

open class Parent

class Child : Parent()

// Extension functions
fun Parent.greet() = "parent"

fun Child.greet() = "child"
