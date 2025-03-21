package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Loops")

    // while-do
    println(" > while-do")
    var i: Int = 1
    while (i <= 3) {
        println(" > $i")
        i = i+1
    }

    // do-while
    println(" > do-while")
    var j: Int = 3
    do {
        println(" > $j")
        j = j - 1
    } while (j >= 1)

    // for-loop
    println(" > for-loop")
    val vowels: List<Char> = listOf('a', 'e', 'i', 'o', 'u')
    for (vowel in vowels) {
        println(" > $vowel")
    }

    // Iterating over a map
    println(" > Map")
    val map: Map<Int, String> = mapOf(
        1 to "one",
        2 to "two",
        3 to "three"
    )
    for ((key, value) in map) {
        println(" > [key: $key, value: $value]")
    }

    // Iterating over list with index
    println(" > List withIndex")
    val letters = listOf('F', 'e', 'r', 'r', 'i', 's')
    for ((index, element) in letters.withIndex()) {
        println(" > [index: $index, element: $element]")
    }

    // Iterating over a range, upper bound included
    println(" > Range, upper bound included")
    for (count in 11..15) {
        println(" > $count")
    }

    // Iterating over a range, upper bound excluded
    println(" > Range, upper bound excluded")
    for (j in 1 until 6) {
        println(" > $j")
    }

    // Iterating with a step
    println(" > Range, with step")
    for (k in 10 downTo 0 step 2) {
        println(" > $k")
    }

    // Iterating over String
    println(" > Iteration over String")
    for (ch in "Hello") {
        println(" > $ch")
    }

}