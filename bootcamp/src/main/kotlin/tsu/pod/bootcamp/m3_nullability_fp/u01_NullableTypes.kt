package tsu.pod.bootcamp.m3_nullability_fp

fun main() {
    println("Nullable Types")

    /**
     * Nullable
     * - To make a type nullable, add a question mark at the end of its name
     */
    val nullable: String? = null
    println(" > nullable: $nullable")

    /**
     * Safe Access
     * - You cannot dereference a variable of a nullable type
     *   println(" > length: ${nullable.length}")
     */
    val length1: Int? = nullable?.length
    println(" > length1: $length1")
    if (nullable != null) {
        println(" > length1: $nullable")
    }

    /**
     * Elvis Operator
     * - Default value instead of null
     */
    val length2: Int = nullable?.length ?: 0
    println(" > length2: $length2")

    /**
     * Control-Flow Analysis
     * - If you explicitly check that the reference is null and call
     *   the fail() function, then afterward you can access the reference
     *   without safe access
     *
     *   if (s == null) return
     *   s.length
     */

    /**
     * Throw NullPointerException (!!)
     * - By default, try to avoid it
     */
    val name: String? = "John Wick"
    println(" > name: ${name!!}")

    /**
     * - We can't pass null whenever non-nullable type is expected
     *   printName(null)
     */
}

fun printName(name: String) {
    println(" > The name is: $name")
}
