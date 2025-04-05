package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Class Modifiers Two")

    /**
     * sealed modifier
     * - restricts class hierarchy
     * - all subclasses must be located in the same file
     */
    println(" > expr: " + eval(Sum(Num(20), Num(30))))

}

sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(expr: Expr): Int = when (expr) {
    is Num -> expr.value
    is Sum -> eval(expr.left) + eval(expr.right)
}