package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    var secretArray: CharArray = secret.toCharArray()
    var guessArray: CharArray = guess.toCharArray()
    var rightPosition: Int = 0
    var wrongPosition: Int = 0

    for (i in 0..3) {
        if (guessArray[i] == secretArray[i]) {
            rightPosition++
            secretArray[i] = '*'
            guessArray[i] = '-'
        }
    }

    for (i in 0..3) {
        val index = secretArray.indexOf(guessArray[i])
        if (index >= 0) {
            wrongPosition++
            secretArray[index] = '*'
        }
    }

    return Evaluation(rightPosition, wrongPosition)

}

fun evaluateGuessV2(secret: String, guess: String): Evaluation {
    val rightPositions = secret.zip(guess).count {
        it.first == it.second
    }
    val commonLetters = "ABCDEF".sumBy {
        ch ->
            Math.min(secret.count{it == ch}, guess.count{it == ch})
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}