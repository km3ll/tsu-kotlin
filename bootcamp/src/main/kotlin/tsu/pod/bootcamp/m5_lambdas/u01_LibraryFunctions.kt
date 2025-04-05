package tsu.pod.bootcamp.m5_lambdas

fun main() {

    println("Library Functions")

    /**
     * run
     * - runs a lambda and returns the last expression as result
     */
    val greet: Unit = run {
        println(" > hello pod")
    }
    greet

    /**
     * let
     * - allows to check the argument for being non-null
     */
    val user: String? = "John Wick"
    user.let { sendEmail(it) }

    /**
     * takeIf
     * - returns the receiver object if it satisfies the predicate
     *   otherwise returns null
     */
    val sName: String = "Genos"
    val sHero: String? = sName.takeIf { it.startsWith("G") }
    println(" > s-class hero: $sHero")

    /**
     * takeUnless
     * - returns the receiver object if it does not satisfy the predicate,
     *   otherwise returns null
     */
    val cName: String = "Mumen"
    val cHero: String? =  cName.takeUnless { it.startsWith("S") }
    println(" > c-class hero: $cHero")

    /**
     * repeat
     * - repeats an action for a given number of times
     */
    repeat(3) {
        println(" > Saitama")
    }

}

fun sendEmail(receiver: String?) {
    println(" > email sent to: $receiver")
}