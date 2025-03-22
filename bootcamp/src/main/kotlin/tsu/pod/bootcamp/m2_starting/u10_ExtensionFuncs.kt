package tsu.pod.bootcamp.m2_starting

fun main() {
    /**
     * Extension Functions
     * - Extend a class
     * - Defined outside the class
     * - Can be called inside the class
     * - 'this.' notation can be omitted
     *
     * Static Functions
     * - Extensions are regular static functions
     *   defined in a separate auxiliary class
     * - You can't call private members from extensions
     *
     * Need to be imported before usage
     * Receiver
     * - the type that the function extends
     */
    println("Extension Functions")
    println(" > lastChar: ${"Ferris".lastChar()}")

    val item = Item(id = 1100, "Nokia")
    println(" > toJson: ${item.toJson()}")
}

/**
 * Receiver
 * - The type that the function extends
 */
class Item(
    val id: Int,
    val name: String
)

fun String.lastChar(): Char {
    return this.get(this.length - 1)
}

/**
 * 'this.' notation can be omitted
 */
fun Item.toJson(): String {
    return "{\"id\":$id,\"name\":\"$name\"}"
}