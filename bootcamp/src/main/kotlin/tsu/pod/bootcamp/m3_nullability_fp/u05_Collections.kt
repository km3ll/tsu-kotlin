package tsu.pod.bootcamp.m3_nullability_fp

fun main() {
    println("Collections")
    val digits = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val words = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val students =
        listOf(
            Student("Alice", 31),
            Student("Bob", 29),
            Student("Carol", 31),
        )

    println(" > filter")
    digits
        .filter { it % 2 == 0 }
        .forEach { println("   $it") }

    println(" > map")
    words
        .map { it.uppercase() }
        .take(5)
        .forEach { println("   $it") }

    println(" > any")
    println("   ${digits.any { it == 10 }}")

    println(" > all")
    println("   ${digits.all { it >= 0 }}")

    println(" > none")
    println("   ${digits.none { it < 0 }}")

    println(" > find")
    println("   ${words.find { it == "ten" }}")

    println(" > firstOrNull")
    println("   ${words.firstOrNull { it.startsWith("e") }}")

    println(" > count")
    println("   ${words.count { it.startsWith("t") }}")

    println(" > partition")
    val res1: Pair<List<Int>, List<Int>> = digits.partition { it < 5 }
    println("   first: ${res1.first}")
    println("   second: ${res1.second}")

    println(" > partition")
    val res2: Map<Int, List<Student>> = students.groupBy { it.age }
    res2.forEach { println("   $it") }

    /**
     * associatedBy
     * - when key is unique
     * - duplicates are removed
     */
    println(" > associatedBy (unique key)")
    val res3: Map<String, Student> = students.associateBy { it.name }
    res3.forEach { println("   $it") }

    /**
     * associate
     * - key-value pairs
     */
    println(" > associated")
    val res4: Map<Char, Int> =
        digits
            .take(5)
            .associate { 'a' + it to 10 * it }
    res4.forEach { println("   $it") }

    /**
     * zip
     * - organizes a couple of lists
     * - the resulting list of pairs has the length of the shortest list
     */
    println(" > zip")
    val res5: List<Pair<Int, String>> =
        digits
            .take(5)
            .zip(words.take(5))
    res5.forEach { println("   $it") }

    /**
     * zipWithNext
     * - returns a list of pairs where each pair are neighboring elements
     * - each element, except the first and the last, will belong to two pairs
     */
    println(" > zipWithNext")
    val res6: List<Pair<Int, Int>> =
        digits
            .take(5)
            .zipWithNext()
    res6.forEach { println("   $it") }

    println(" > flatten")
    val res7 =
        listOf(
            digits.take(2),
            digits.take(2),
            digits.take(2),
        ).flatten()
    res7.forEach { println("   $it") }

    println(" > flatMap")
    val res8 =
        words
            .take(2)
            .flatMap { it.toCharArray().toList() }
    res8.forEach { println("   $it") }
}

data class Student(
    val name: String,
    val age: Int,
)
