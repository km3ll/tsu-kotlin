package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {
    println("Constructors")

    /**
     * Main constructor
     * - a.k.a. primary constructor
     * - init: constructor body
     * - If you put 'val' or 'var' before the parameter, that
     *   automatically creates a property.
     * - Without 'val' or 'var' it's only a constructor parameter
     */
    val note = Note(id = 1100)
    println(" > content: ${note.content}")

    /**
     * Inheritance
     * - You use colon ':' to replace 'extends' and 'implements'
     * - Use the 'super' keyword to call the constructor of the
     *   parent class from the secondary constructor
     */

    /**
     * Initialization Order
     * - parent
     * - child
     */
    val childOne = ChildOne()
    val childTwo = ChildTwo()

}

class Note(id: Int, content: String) {

    val id: Int
    val content: String

    // Primary constructor body
    init {
        this.id = id
        this.content = content
    }

    // Secondary constructor
    constructor(id: Int) : this(id, "default")

}

class Component() {
    // Visibility of constructor
    internal constructor(name: String) : this() {
    }
}

interface Base
class BaseImpl : Base

open class Parent {
    init {
        println(" > parent")
    }
}

class ChildOne : Parent() {
    init {
        println(" > child one")
    }
}

class ChildTwo : Parent {
    constructor(): super()
    init {
        println(" > child two")
    }
}