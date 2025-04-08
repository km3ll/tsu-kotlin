package tsu.pod.bootcamp.m2_starting

import java.time.LocalDateTime

fun main() {
    println("In: Checks & Ranges")

    // In: Belonging
    println(" > In: belonging")
    println("   > isLetter: ${isLetter('k')}")
    println("   > isLetter: ${isLetter('7')}")
    println("   > isNotDigit: ${isNotDigit('h')}")

    // In: when-condition
    println(" > In: when-condition")
    println("   > recognize: ${recognize('&')}")

    // Range types
    println(" > Range: types")
    val intRange: IntRange = 1..9
    println("   > intRange: $intRange")
    val charRange: CharRange = 'a'..'z'
    println("   > charRange: $charRange")
    val stringRange: ClosedRange<String> = "ab".."az"
    println("   > stringRange: $stringRange")

    // Range: alphabetical order
    println(" > Range: alphabetical ")
    val isAlphabetical: Boolean = "Kotlin" in "Java".."Scala"
    println("   > isAlphabetical: $isAlphabetical")

    // Range: Comparable interface
    println(" > Range: comparable")
    val current: LocalDateTime = LocalDateTime.now()
    println("   > current: $current")
    val future = current.plusMonths(1)
    println("   > future: $future")
    val isBefore = current < future
    println("   > isBefore: $isBefore")

    // In: collection
    println(" > In:  collection")
    val letters = ('a'..'z').toList()
    val inLetters = 'k' in letters
    println("   > inLetters: $inLetters")
}

fun isLetter(c: Char): Boolean = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char): Boolean = c !in '0'..'9'

fun recognize(c: Char) =
    when (c) {
        in '0'..'9' -> "It's a digit!"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
        else -> "I don't know"
    }
