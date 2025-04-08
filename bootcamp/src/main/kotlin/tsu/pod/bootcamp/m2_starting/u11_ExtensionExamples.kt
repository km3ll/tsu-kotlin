package tsu.pod.bootcamp.m2_starting

fun main() {
    println("Extension Examples")

    /**
     * Kotlin Standard Library = Java Standard Library + Extensions
     * - No Kotlin SDK
     * - small runtime jar
     * - doesn't duplicate Java implementations
     */
    println(" > javaClass")
    val set: HashSet<Int> = hashSetOf(1, 3, 5)
    println("   > set: ${set.javaClass}")
    val list: List<Int> = arrayListOf(2, 4, 6)
    println("   > list: ${list.javaClass}")
    val map: HashMap<Int, String> = hashMapOf(1 to "one", 2 to "two", 3 to "three")
    println("   > map: ${map.javaClass}")

    println(" > Examples")

    val joined =
        listOf('f', 'e', 'r', 'r', 'i', 's')
            .joinToString(separator = " ", prefix = "[", postfix = "]")
    println("   > joinToString: $joined")

    val res1 = arrayOf(1, 2, 3, 4).getOrNull(10)
    println("   > array.getOrNull: $res1")

    val res2 = listOf(1, 2, 3, 4).getOrNull(10)
    println("   > list.getOrNull: $res2")

    val res3 = listOf("One", "Two", "Three").withIndex()
    println("   > list.withIndex")
    for ((index, elem) in res3) {
        println("     $index - $elem")
    }

    println("   > until (infix)")
    // infix fun Int.until(to: Int): IntRange

    println("   > to (infix)")
    // infix fun <A, B> A.to(that: B) = Pair(this, that)

    val res4: Boolean = 'a'.isLetter()
    println("   > isLetter: $res4")

    val res5: Boolean = '%'.isDigit()
    println("   > isDigit: $res5")

    val res6: String =
        """
        To code,
        or not to code?
        """.trimIndent()
    println("   > multiLine > trimIndent: $res6")

    val res7: String =
        """
    To code,
    #or not to code?
    """.trimMargin(marginPrefix = "#")
    println("   > multiLine > trimMargin: $res7")

    val regex1: Regex = "\\d{2}\\.\\d{2}\\.\\d{4}".toRegex()
    val res8: Boolean = regex1.matches("15.02.2016")
    println("   > regex: $res8")

    val regex2: Regex = """\d{2}\.\d{2}\.\d{4}""".toRegex()
    val res9: Boolean = regex2.matches("15.02.16")
    println("   > regex: $res9")

    val res10 = "1100".toInt()
    println("   > toInt: $res10")

    // val res11 = "x".toInt() # NumberFormatException
    val res11 = "x".toIntOrNull()
    println("   > toIntOrNull: $res11")

    val res12 = getAnswer() eq 42
    println("   > eq: $res12")
}

infix fun <T> T.eq(other: T) {
    if (this == other) {
        println("OK")
    } else {
        println("Error: $this != $other")
    }
}

fun getAnswer(): Int = 42
