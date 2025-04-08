package rationals

import java.math.BigInteger

fun main() {

    val half: Rational = 1 divBy 2
    val third: Rational = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)

}

data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {

    constructor(numerator: BigInteger) : this(numerator = numerator, denominator = BigInteger.valueOf(1))

    init {
        if (denominator == BigInteger.valueOf(0)) throw IllegalArgumentException("Denominator value cannot be Zero")
    }

    override fun toString(): String {
        if (this.denominator == BigInteger.valueOf(1)) {
            return this.numerator.toString()
        }
        if (this.numerator < BigInteger.valueOf(0) && this.denominator < BigInteger.valueOf(0)) {
            return "${this.numerator * BigInteger.valueOf(-1)}/${this.denominator * BigInteger.valueOf(-1)}"
        }
        if (this.denominator < BigInteger.valueOf(0)) {
            return "${this.numerator * BigInteger.valueOf(-1)}/${this.denominator * BigInteger.valueOf(-1)}"
        }
        return "${this.numerator}/${this.denominator}"
    }

    override fun compareTo(other: Rational): Int {
        return (this.numerator * other.denominator).compareTo(other.numerator * this.denominator)
    }

    fun normalize(): Rational {
        val gcd: BigInteger = numerator.gcd(denominator)
        val normalized = Rational(
            numerator = numerator / gcd,
            denominator = denominator / gcd
        )
        println("normalized: $normalized")
        return normalized
    }

}

operator fun Rational.plus(other: Rational): Rational {
    val thisNorm = this.normalize()
    val otherNorm = other.normalize()
    return Rational(
        numerator = (thisNorm.numerator * otherNorm.denominator) + (otherNorm.numerator * thisNorm.denominator),
        denominator = thisNorm.denominator * otherNorm.denominator,
    ).normalize()
}

operator fun Rational.minus(other: Rational): Rational {
    val thisNorm = this.normalize()
    val otherNorm = other.normalize()
    return Rational(
        numerator = (thisNorm.numerator * otherNorm.denominator) - (otherNorm.numerator * thisNorm.denominator),
        denominator = thisNorm.denominator * otherNorm.denominator,
    ).normalize()
}

operator fun Rational.times(other: Rational): Rational {
    val thisNorm = this.normalize()
    val otherNorm = other.normalize()
    return Rational(
        numerator = thisNorm.numerator * otherNorm.numerator,
        denominator = thisNorm.denominator * otherNorm.denominator,
    ).normalize()
}

operator fun Rational.unaryMinus(): Rational {
    val thisNorm = this.normalize()
    return Rational(
        numerator = -thisNorm.numerator,
        denominator = thisNorm.denominator,
    ).normalize()
}

operator fun Rational.div(other: Rational): Rational {
    val thisNorm = this.normalize()
    val otherNorm = other.normalize()
    return Rational(
        numerator = thisNorm.numerator * otherNorm.denominator,
        denominator = thisNorm.denominator * otherNorm.numerator,
    ).normalize()
}

infix fun Int.divBy(other: Int): Rational {
    return Rational(
        numerator = this.toBigInteger(),
        denominator = other.toBigInteger()
    ).normalize()
}

infix fun Long.divBy(other: Long): Rational {
    return Rational(
        numerator = this.toBigInteger(),
        denominator = other.toBigInteger()
    ).normalize()
}

infix fun BigInteger.divBy(other: BigInteger): Rational {
    return Rational(
        numerator = this,
        denominator = other
    ).normalize()
}

fun String.toRational(): Rational {
    if (this.contains("/")) {
        val values: List<String> = this.split("/")
        val numerator = values[0].toBigInteger()
        val denominator = values[1].toBigInteger()

        if (denominator < BigInteger.valueOf(0)) {
            return Rational(
                numerator = numerator * BigInteger.valueOf(-1),
                denominator = denominator * BigInteger.valueOf(-1),
            ).normalize()
        } else {
            return Rational(
                numerator = values[0].toBigInteger(),
                denominator = values[1].toBigInteger(),
            ).normalize()
        }
    } else {
        return Rational(
            numerator = this.toBigInteger(),
            denominator = BigInteger.valueOf(1),
        ).normalize()
    }
}