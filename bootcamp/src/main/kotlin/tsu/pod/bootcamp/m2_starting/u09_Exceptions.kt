package tsu.pod.bootcamp.m2_starting

fun main() {
    /**
     * Exceptions
     * - No difference between checked and unchecked exceptions
     * - 'throw' is an exception in Kotlin
     */
    println("Exceptions")
    println(" > throw: expression")
    val number = 10
    val percentage =
        if (number in 0..100) {
            number
        } else {
            throw IllegalArgumentException("A percentage value must be between 0 and 100: $number")
        }
    println("   > percentage: $percentage")

    println(" > try: expression")
    val parsed =
        try {
            Integer.parseInt("H")
        } catch (e: NumberFormatException) {
            println("   > not-a-number")
        }
}
