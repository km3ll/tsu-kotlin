package tsu.pod.bootcamp.m3_nullability_fp

import kotlin.reflect.KFunction2

fun main() {

    println("Member References")

    val users = listOf(
        User(name = "John", age = 35),
        User(name = "Anne", age = 25),
        User(name = "Karol", age = 30),
    )
    val maxUser: User = users.maxBy { it.age }
    println(" > maxUser: $maxUser")

    /**
     * Lambdas and Functions
     * - You can store a lambda in a variable
     * - You can't store a function in a variable
     *   - User function reference syntax
     */
    val predicate = ::isEven

    /**
     * Passing function reference as an argument
     */
    listOf(1, 2, 3, 4, 5, 6)
        .filter(::isEven)
        .forEach { println(" > it: $it") }

    /**
     * Non-bound reference
     * - not linked to specific instances
     */
    val agePredicate1: (User, Int) -> Boolean = User::isOlder
    val alice = User(name = "Alice", age = 29)
    println(" > non-bound: ${agePredicate1(alice, 21)}")

    /**
     * Bound reference
     * - linked to specific instances
     */
    val bob = User(name = "Bob", age = 29)
    val agePredicate2: (Int) -> Boolean = bob::isOlder
    println(" > non-bound: ${agePredicate2(21)}")


}

/**
 * Bound to 'this' reference
 */
data class User(val name: String, val age: Int) {

    fun isOlder(ageLimit: Int) = age > ageLimit

    fun getAgePredicate() = this.isOlder(21)

}

fun isEven(i: Int): Boolean = i % 2 == 0