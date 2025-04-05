package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("InLine")

    /**
     * inLine function
     * - the compiler substitutes a body of the function instead of calling it
     *   inline fun <R> run(block: () -> R): R = block()
     */
    val name = "Pod"

    // brings performance overhead
    myRun { println(" > Hi, $name") }

    // in comparison to
    println(" > Hi, $name")

    /**
     * Functions
     * - run
     * - takeUnless
     * - synchronized
     * - lock
     * - withLock
     * - readFirstLineFromFile
     * - use
     */

    /**
     * No performance overhead when you use
     * - run
     * - let
     * - takeIf
     * - takeUnless
     * - repeat
     * - withLock
     * - use
     */

    /**
     * @InlineOnly
     * - specifies that this function should not be called directly
     *   without inlining
     *   @kotlin.internal.InLineOnly
     *   public inline fun <R> run(block: () -> R): R = block()
     */

}

fun myRun(f: () -> Unit) = f()