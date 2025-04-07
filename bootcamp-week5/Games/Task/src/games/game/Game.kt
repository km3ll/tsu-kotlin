package games.game

import board.Direction
import games.gameOfFifteen.GameOfFifteenInitializer

interface Game {

    fun initialize()

    fun canMove(): Boolean

    fun hasWon(): Boolean

    fun processMove(direction: Direction)

    operator fun get(i: Int, j: Int): Int?

}

class GameImpl(val initializer: GameOfFifteenInitializer): Game {

    var permutation = mutableListOf<Int>()

    override fun initialize() {
        permutation.addAll(initializer.initialPermutation)
    }

    override fun canMove(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasWon(): Boolean {
        TODO("Not yet implemented")
    }

    override fun processMove(direction: Direction) {
        TODO("Not yet implemented")
    }

    override fun get(i: Int, j: Int): Int? {
        TODO("Not yet implemented")
    }

}