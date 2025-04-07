package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("Collection Types")

    /**
     * List & MutableList
     * - two interfaces declared in kotlin.collections package
     * - MutableList extends List
     *
     * - kotlin.List
     *   - kotlin.MutableList
     *     - java.util.ArrayList
     *
     * - read-only interfaces improve API
     */

    data class Person(val name: String, val age: Int)

    val people = listOf(Person("Alice", 30), Person("Bob", 25), Person("Charlie", 35))
    val names = people.map(Person::name)
    println(names)



}