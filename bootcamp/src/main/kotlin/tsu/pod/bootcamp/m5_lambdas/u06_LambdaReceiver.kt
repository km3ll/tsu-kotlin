package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("Lambda Receiver")

    /**
     * Lambda with receiver
     * - 'with' is a function
     * - lambda is its second argument
     * - 'this' is an implicit receiver in the lambda
     * - 'this' can be omitted
     */
    val sb = StringBuilder()
    with (sb) {
        appendLine(" > alphabet: ")
        for (c in 'a'..'z') {
            append(c)
        }
        this.toString()
    }
    println(sb)

    /**
     * Storing a lambda with receiver
     */
    val isOdd: Int.() -> Boolean = { this % 2 == 1}
    println(" > isOdd: ${isOdd(100)}")

    /*
    HTML Builders (lambdas with receivers)
    html {
        table {
            for (product in products) {
                td { text(product.description) }
                td { text(product.price) }
                td { text(product.popularity) }
            }
        }
    }
    */

    /**
     * with
     * - takes an expression as an argument and uses it as receiver
     */
    val window1 = Window(100, 100, true)
    with(window1) {
        println(" > width: $width")
        println(" > height: $height")
        println(" > isVisible: $isVisible")
    }

    /**
     * run
     * - allows to use a nullable receiver
     * - implements safe access
     */
    val window2: Window? = null
    window2?.run {
        println(" > width: $width")
        println(" > height: $height")
        println(" > isVisible: $isVisible")
    }

    /**
     * apply
     * - returns the receiver as a result
     */
    val window3: Window? = Window(20, 20, false)
    val window4 = window3?.apply {
        width = 80
        height = 80
        isVisible = true
    }
    println(" > window4: $window4")

    /**
     * also
     * - to pass the receiver as an argument
     */
    val window5: Window? = Window(0, 0, false)
    window5?.apply {
        width = 22
        height = 22
        isVisible = true
    }?.also { show(it) }

}

data class Window(
    var width: Int,
    var height: Int,
    var isVisible: Boolean
)

fun show(window: Window) {
    println(" > window5: $window")
}