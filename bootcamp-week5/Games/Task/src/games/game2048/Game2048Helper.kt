package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
        var merged = mutableListOf<T>()
        var internal = mutableListOf<T>()
        internal.addAll(this.filterNotNull())
        var index = 0
        while(index <= internal.size - 1) {
                if (hasNext(index, internal)) {
                        val current = internal[index]
                        val next = internal[index+1]
                        if (current == next) {
                                merged.add(merge(current))
                                index++
                        } else {
                                merged.add(current)
                        }
                } else {
                        merged.add(internal[index])
                }
                index++
        }
        return merged
}


fun <T> hasNext(index: Int, internal: List<T>): Boolean {
        return index < internal.size - 1
}