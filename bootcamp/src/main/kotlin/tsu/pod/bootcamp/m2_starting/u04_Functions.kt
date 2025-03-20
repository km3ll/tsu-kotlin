//@file:JvmName("Functions")
package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Functions")

    /**
     * Functions
     * - top-level function
     * - member function
     * - local function
     *
     * From Java you call a top-level function as a static function
     * Annotation @JvmName changes the JVM name of the class containing
     * top-level functions
     */
    println(" > max v1: ${maxV1(10, 20)}")
    println(" > max v2: ${maxV2(20, 10)}")
    display(name = "Pod")

    val lamp = Lamp()
    println(" > isOn: ${lamp.isOn()}")

    lamp.turnOn()
    println(" > isOn: ${lamp.isOn()}")

}

fun maxV1(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun maxV2(a: Int, b: Int): Int = if (a > b) a else b

// Top-level function
fun display(name: String): Unit {
    // Local function
    fun print(name: String): Unit {
        println(" > name: $name")
    }
    print(name)
}

class Lamp{
    private var isOn: Boolean = false
    // Member function
    fun turnOn() {
        isOn = true
    }
    fun turnOff() {
        isOn = false
    }
    fun isOn(): Boolean = isOn
}