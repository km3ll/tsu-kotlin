package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Constants")

    /**
     * Constants
     * - 'const' modifier only for primitive types and Strings
     *   const val answer = 42
     * - @JvmField
     *   exposes a property as a field
     *   @JvmField (no getter is generated)
     *   val prop = MyClass()
     */
    println(" > static")
    println("   > answer1: " + Computer.answer1)
    println("   > answer2: " + Computer.answer2)
    println("   > answer3: " + Computer.answer3)

}

object Computer {

    @JvmStatic
    val answer1 = 10

    @JvmField
    val answer2 = 20

    const val answer3 = 30

}