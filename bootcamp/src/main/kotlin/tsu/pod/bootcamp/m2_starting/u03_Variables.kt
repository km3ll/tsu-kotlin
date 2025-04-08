package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Variables")
    /**
     * Variables
     * - val = value (immutable)
     * - var = variable (mutable)
     */
    var answer: Int = 0
    answer = 42
    println(" > answer: $answer")

    /**
     * Objects
     * - Is it possible to modify an object store in val?
     *   Yes, val like a final variable is an immutable reference
     *   which doesn't say anything about the content that is stored
     * - You can't modify a read-only list such as:
     *   val list = listOf("Java")
     *   list.add("Kotlin")
     * - Read-only lists lack mutating methods
     */
    val mutableList = mutableListOf("Java")
    mutableList.add("Kotlin")
    println(" > languages: $mutableList")

    val readOnlyList = listOf("Rust")
    // readOnlyList.add("Kotlin") Error: unresolved reference
    println(" > languages: $readOnlyList")
}
