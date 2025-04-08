package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {
    println("Generics")
}

fun <T> List<T>.filtere(predicate: (T) -> Boolean): List<T> {
    val destination = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) destination.add(element)
    }
    return destination
}

fun use1(ints: List<Int>) {
    ints.filtere { it > 0 }
}

fun use2(strings: List<String>) {
    strings.filtere { it.isNotEmpty() }
}

fun use3(ints: List<Int?>) {
    ints.filtere { it != null && it > 0 }
}

fun use4(strings: List<String?>) {
    strings.filtere { !it.isNullOrEmpty() }
}

/**
 * Nullable generics
 */
// fun <T> List<T>.firstOrNull: T?

/**
 * Non-Nullable upper bound
 * - fun <T: Any> foo(list: List<T>) {
 */