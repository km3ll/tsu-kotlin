package tsu.pod.bootcamp.m3_nullability_fpp

fun main() {

    println("Nullable Details")

    /**
     * Nullable Types
     * - Implemented with annotations: @Nullable, @NotNull
     *   Do not create additional objects
     * - Also implemented with Optional<T>
     *   Create an extra object
     */

    // List of nullable Ints
    val list1: List<Int?> = listOf(1, 2, null, 4)
    list1.forEach { number -> println(" > no: $number") }

    // Nullable list of Ints
    val list2: List<Int>? = null
    println(" > list: $list2")

}
