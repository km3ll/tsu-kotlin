package tsu.pod.bootcamp.m3_nullability_fp

fun main() {

    println("Return From Lambda")

    /// Return from whole function
    val result1 = duplicateNonZeroV1(listOf(3, 0, 5))
    println(" > result1: $result1")

    // Return from lambda using default label
    val result2 = duplicateNonZeroV2(listOf(3, 0, 5))
    println(" > result2: $result2")

    // Return from lambda using custom label
    val result3 = duplicateNonZeroV3(listOf(3, 0, 5))
    println(" > result3: $result3")

    // Return using local function
    val result4 = duplicateNonZeroV4(listOf(3, 0, 5))
    println(" > result4: $result4")

    // Return from lambda using anonymous function
    val result5 = duplicateNonZeroV5(listOf(3, 0, 5))
    println(" > result5: $result5")

    // Return from lambda without using 'return'
    val result6 = duplicateNonZeroV6(listOf(3, 0, 5))
    println(" > result6: $result6")

}

/**
 * Return from whole function
 */
fun duplicateNonZeroV1(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) return listOf()
        listOf(it, it)
    }
}

/**
 * Return from lambda using default label
 */
fun duplicateNonZeroV2(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) return@flatMap listOf() // When 0 is found then execution finishes
        listOf(it, it)
    }
}

/**
 * Return from lambda using custom label
 */
fun duplicateNonZeroV3(list: List<Int>): List<Int> {
    return list.flatMap hakuna@{
        if (it == 0) return@hakuna listOf() // When 0 is found then execution finishes
        listOf(it, it)
    }
}

/**
 * Return using local function
 */
fun duplicateNonZeroV4(list: List<Int>): List<Int> {
    fun innerFunction(elem: Int): List<Int> {
        if (elem == 0) return listOf() // When 0 is found then execution finishes
        else return listOf(elem, elem)
    }
    return list.flatMap(::innerFunction)
}

/**
 * Return from lambda using anonymous function
 */
fun duplicateNonZeroV5(list: List<Int>): List<Int> {
    return list.flatMap( fun(elem): List<Int> {
        if (elem == 0) return listOf() // When 0 is found then execution finishes
        else return listOf(elem, elem)
    })
}

/**
 * Return from lambda without using 'return'
 */
fun duplicateNonZeroV6(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0)
            listOf()
        else
            listOf(it, it)
    }
}