package games.game

import board.Cell
import board.Direction
import board.GameBoardImpl
import games.gameOfFifteen.GameOfFifteenInitializer

interface Game {

    fun initialize()

    fun canMove(): Boolean

    fun hasWon(): Boolean

    fun processMove(direction: Direction)

    operator fun get(i: Int, j: Int): Int?

}

class GameImpl(val initializer: GameOfFifteenInitializer): Game {

    private val winingResult = mutableListOf<Int?>()
    private val gameBoard = GameBoardImpl<Int>(4)

    override fun initialize() {
        val values = mutableListOf<Int?>()
        values.addAll(initializer.initialPermutation)
        values.add(null)
        setValues(values)
        setWining()
    }

    override fun canMove(): Boolean {
        val hasWon = hasWon()
        return !hasWon
    }

    override fun hasWon(): Boolean {
        val result: List<Int?> = gameBoard.getAllCells()
            .map { cell -> gameBoard.get(cell) }
        return result == winingResult
    }

    override fun processMove(direction: Direction) {

        val empty: Cell = gameBoard
            .getAllCells()
            .first { cell -> gameBoard.get(cell) == null }
        println("empty: $empty")

        when (direction) {
            Direction.UP -> processUp(empty)
            Direction.DOWN -> processDown(empty)
            Direction.LEFT -> processLeft(empty)
            Direction.RIGHT -> processRight(empty)
        }

    }

    override fun get(i: Int, j: Int): Int? {
        val cell = gameBoard.getCell(i, j)
        return gameBoard.get(cell)
    }

    private fun setValues(values: List<Int?>) {
        var index = 0
        for (cell in gameBoard.getAllCells()) {
            gameBoard.set(cell, values[index])
            index++
        }
    }

    private fun setWining() {
        winingResult.addAll(1..15)
        winingResult.add(null)
    }

    fun processUp(empty: Cell) {
        if (empty.i == 4) {
            println("Cannot move up")
        } else {
            println("Moving up")
            val cell: Cell = gameBoard.getCell(empty.i + 1, empty.j)
            val value: Int? = gameBoard.get(cell)
            gameBoard.set(cell, null)
            gameBoard.set(empty, value)
        }
    }

    fun processDown(empty: Cell) {
        if (empty.i == 1) {
            println("Cannot move down")
        } else {
            println("Moving down")
            val cell: Cell = gameBoard.getCell(empty.i - 1, empty.j)
            val value: Int? = gameBoard.get(cell)
            gameBoard.set(cell, null)
            gameBoard.set(empty, value)
        }
    }

    fun processLeft(empty: Cell) {
        if (empty.j == 4) {
            println("Cannot move left")
        } else {
            println("Moving left")
            val cell: Cell = gameBoard.getCell(empty.i, empty.j + 1)
            val value: Int? = gameBoard.get(cell)
            gameBoard.set(cell, null)
            gameBoard.set(empty, value)
        }
    }

    fun processRight(empty: Cell) {
        if (empty.j == 1) {
            println("Cannot move right")
        } else {
            println("Moving right")
            val cell: Cell = gameBoard.getCell(empty.i, empty.j - 1)
            val value: Int? = gameBoard.get(cell)
            gameBoard.set(cell, null)
            gameBoard.set(empty, value)
        }
    }

}