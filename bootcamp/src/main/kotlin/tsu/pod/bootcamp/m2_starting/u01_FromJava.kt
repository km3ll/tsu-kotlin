package tsu.pod.bootcamp.m2_starting

fun main() {
    println("From Java")
    val person = Person("John", 33)
    val pet = Pet("Chopper", 2)
    println(" > person: $person")
    println(" > name: ${person.name}")
    println(" > pet: $pet")
    println(" > enum: ${Color.BLUE}")
    println(" > pair:")
    println("   > ${getWeatherPair(5)}")
    println("   > ${getWeatherWhen(20)}")
    println("   > ${getWeatherTo(30)}")
}

/**
 * Class
 * - constructor
 * - getters
 */
class Person(
    val name: String,
    val age: Int,
)

/**
 * Modifier: data
 * - equals
 * - hashCode
 * - toString
 */
data class Pet(
    val name: String,
    val age: Int,
)

enum class Color {
    BLUE,
    ORANGE,
    RED,
}

fun getWeatherPair(degrees: Int): String {
    val (description: String, color: Color) =
        if (degrees < 10) {
            Pair("cold", Color.BLUE)
        } else if (degrees < 25) {
            Pair("mild", Color.ORANGE)
        } else {
            Pair("hot", Color.RED)
        }
    return ("degrees: $degrees, color: $color, description: $description")
}

fun getWeatherWhen(degrees: Int): String {
    val (description, color) =
        when {
            degrees < 10 -> Pair("cold", Color.BLUE)
            degrees < 25 -> Pair("mild", Color.ORANGE)
            else -> Pair("hot", Color.RED)
        }
    return ("degrees: $degrees, color: $color, description: $description")
}

fun getWeatherTo(degrees: Int): String {
    val (description, color) =
        when {
            degrees < 10 -> "cold" to Color.BLUE
            degrees < 25 -> "mild" to Color.ORANGE
            else -> "hot" to Color.RED
        }
    return ("degrees: $degrees, color: $color, description: $description")
}
