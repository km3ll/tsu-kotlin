package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Interfaces")

    /**
     * Interfaces
     * - All properties in interfaces are open = not final, so
     *   - cannot be used in smart casts
     *   - can be overridden in subclasses
     */

}

interface User {
    val nickname: String
}

class OnlineUser() : User {
    override val nickname = "king"
}

class NewsUser() : User {
    override val nickname: String
        get() = "genos"
}