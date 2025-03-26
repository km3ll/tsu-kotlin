package tsu.pod.bootcamp.m3_nullability_fp

fun main() {
    println("Function Types")

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val isEven: (Int) -> Boolean = { i: Int -> i % 2 == 0 }

    println(" > sum: ${sum(5, 5)}")
    println(" > isEven: ${isEven(20)}")

    /**
     * Calling a lambda directly
     * - run
     */
    run { println(" > run: Hello, pod") }

    /**
     * SAM Interfaces
     * - Single Abstract Method
     * public interface Runnable {
     *     public abstract void run();
     * }
     */

    /**
     * Function Types and Nullability
     * - () -> Int?
     *   The return type is nullable
     * - (() -> Int)?
     *   The whole type is nullable
     */

    /**
     * Lambda that always returns null
     */
    val lambda1 = { null }

    /**
     * Nullable function
     * - Check for null first
     * - Safe access (invoke)
     */
    val lambda2: (() -> Int)? = null
    // check
    if (lambda2 != null) {
        lambda2()
    }
    // safe access
    lambda2?.invoke()
}
