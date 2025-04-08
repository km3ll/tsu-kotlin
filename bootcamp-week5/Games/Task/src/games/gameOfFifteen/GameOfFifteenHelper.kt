package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    println("permutation: $permutation")
    var counter = 0
    for (index in permutation.indices) {
        val remaining: List<Int> = permutation.subList(fromIndex = index+1, toIndex = permutation.size)
        counter += countInversions(permutation[index], remaining)
    }
    return counter % 2 == 0
}

fun countInversions(value: Int, remaining: List<Int>): Int {
    println("value: $value, remaining: $remaining")
    var count = 0
    remaining.forEach { rem -> if (value > rem) count++ }
    return count
}
