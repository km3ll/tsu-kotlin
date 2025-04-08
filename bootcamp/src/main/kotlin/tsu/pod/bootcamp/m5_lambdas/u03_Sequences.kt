package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("Sequences")

    /**
     * Extensions on collections are inlined,
     * and the return new collections as results
     * - .filter
     * - .map
     * - .any
     * - .find
     * - .groupBy
     */

    /**
     * Operations on collections
     * - lambdas are inlined (no performance overhead)
     * - but intermediate collections are created for chained calls
     */

    /**
     * Sequences
     * - can be compared with Java8 streams
     * - perform computations in a lazy manner
     * - .asSequence()
     */
    val list = listOf(1, 2, -3, -4)
    val maxOddSquare = list
        .asSequence()
        .map { it * it }
        .filter{ it % 2 == 1 }
        .max()
    println(" > max-odd-square: $maxOddSquare")

    /**
     * Horizontal evaluation
     * - collections
     *
     * Vertical evaluation
     * - sequences
     * - when the result is found, remaining operations are discarded
     * - nothing happens until a final operation is called
     */

    /**
     * Order of operations
     *
     * - map().filter()
     *   map gets applied to all elements
     *
     * - filter().map()
     *   map gets applied only to filtered elements
     */

}