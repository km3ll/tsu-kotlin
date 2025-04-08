package tsu.pod.bootcamp.m5_lambdas

import kotlin.random.Random

fun main() {

    println("Creating Sequences")

    /**
     * interface Sequence<out T> {
     *     operator fun iterator(): Iterator<T>
     * }
     */

    /**
     * Sequences
     * - generateSequence()
     * - yield()
     */

    val seq1 = generateSequence {
        Random
            .nextInt(5)
            .takeIf { it > 0 }
    }
    println(" > seq1: ${seq1.toList()}")

    val seq2 = generateSequence {
        readLine().takeIf { it != "exit" }
    }
    //println(" > seq2: ${seq2.toList()}")

    val seq3 = generateSequence(0) { it + 1 }
    println(" > seq3: ${seq3.take(5).toList()}")

    /**
     * yield
     *
     * sequence {
     *   yield( value )
     *   yieldAll( list )
     *   yieldAll( sequence )
     * }
     */
    val seq4 = sequence {
        var x = 0
        while (true) {
            yield(x++)
        }
    }
    println(" > seq4: ${seq4.take(3).toList()}")

    /**
     * yield
     * - you can do any intermediate computations between yieldings
     * - only what is needed gets computed
     */
    val seq5 = sequence {
        println(" > yield ")
        println("   an element")
        yield(1)
        println("   a range")
        yieldAll(3..5)
        println("   a list")
        yieldAll(listOf(7, 9))
    }
    println(" > seq5: ${seq5.take(3).toList()}")
    println(" > seq6: ${seq5.take(3).first()}")

}