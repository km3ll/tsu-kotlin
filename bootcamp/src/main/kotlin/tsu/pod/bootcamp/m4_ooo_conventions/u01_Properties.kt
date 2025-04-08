package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Properties")

    /**
     * Properties
     * - A field with its corresponding setter and getter
     * - property = field + accessors
     *   val = field + getter
     *   var = field = getter = setter
     * - property without fields
     *   - redefine accessor behaviors without storing a value
     *   - executed in each access
     */
    val hero = Hero(id = 1100, name = "")
    hero.name = "saitama"
    println(" > hero: $hero")
    println(" > rank: '${hero.rank}'")
    println(" > isStrongest: ${hero.isStrongest}")

    /**
     * Fields
     * - You can access field only inside accessors
     *   not visible for other methods of the class.
     */
    hero.rank = "C"
    println(" > rank: '${hero.rank}'")

    /**
     * Using Properties
     * - Use properties instead of getter or setter
     */
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hello")
    println(" > counter: ${lengthCounter.counter}")

    /**
     * Visibility of a setter (private)
     * - cannot access 'counter'
     */
    // lengthCounter.counter = 8

}

data class Hero(
    // field + getter
    val id: Int,
    // field = getter = setter
    var name: String
) {

    // property without fields
    val isStrongest: Boolean
        get() {
            return name == "saitama"
        }

    // fields
    var rank = ""
        set(value) {
            println(" > rank changed: '$field' -> '$value'")
            field = value
        }

}

// Using properties
class LengthCounter {

    var counter: Int = 0
        // private setter
        private set

    fun addWord(word: String){
        counter += word.length
    }


}