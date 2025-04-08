package tsu.pod.bootcamp.m3_nullability_fp

fun main() {
    println("Lambdas")

    /**
     * Lambda
     * - Anonymous function that can be used as an expression
     */
    val numbers = listOf("one", "two", "three")
    numbers.forEach { no -> println(" > no: $no") }

    /**
     * Always go in curly braces
     */
    val lambda: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println(" > lambda: ${lambda(1, 9)}")

    /**
     * Lambda as parameter
     */
    val names: List<String> = listOf("John", "Anne")
    // Inside parenthesis
    names.forEach({ name: String -> println(" > name: $name") })
    // Outside parenthesis if lambda is the last parameter
    names.forEach { name: String -> println(" > name: $name") }
    // If parenthesis are empty, you can omit them
    names.forEach { name: String -> println(" > name: $name") }
    // If types can be inferred from context, they be omitted
    names.forEach { name -> println(" > name: $name") }
    // 'it' denotes the argument if it's only one
    names.forEach { println(" > name: $it") }

    /**
     * Destruction Syntax in Maps
     */
    val products: Map<Int, String> = mapOf(1001 to "Coffee", 1002 to "Food")
    // key + value
    products.mapValues { entry ->
        println(" > key: ${entry.key} -> value: ${entry.value}")
    }
    // two parameters
    products.mapValues { (key, value) ->
        println(" > key: $key -> value: $value")
    }
    // unused parameter (underscore)
    products.mapValues { (_, value) ->
        println(" > value: $value")
    }
}
