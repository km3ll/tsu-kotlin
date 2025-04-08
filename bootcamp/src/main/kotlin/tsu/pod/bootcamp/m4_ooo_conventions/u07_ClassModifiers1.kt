package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {
    println("Class Modifiers One")

    /**
     * Enumeration
     */
    println(" > color: ${Color.BLUE}")
    println(" > color: ${Color.BLUE}")
    println(" > code: ${Color.RED.code}")
    Color.ORANGE.printCode()

    /**
     * data class modifier
     * - copy
     * - equals '=='
     * - reference equality '==='
     */

    println(" > copy")
    val frodo = Contact(name = "Frodo", address = "The Shire")
    val sam = frodo.copy("Sam")
    println("   > frodo: $frodo")
    println("   > sam: $sam")

    println(" > equals")
    println("   > ${frodo == sam}")

    println(" > reference equality")
    println("   > ${frodo === sam}")

}

enum class Color(val code: Int) {

    BLUE(1101), ORANGE(1102), RED(1103);

    fun printCode() {
        println(" > code: $code")
    }

}

data class Contact(val name: String, val address: String)