package games.game2048

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game = Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val newValue: Pair<Cell, Int>? = initializer.nextValue(this)
    if (newValue != null) {
        this.set(newValue.first, newValue.second)
    }
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {

    val values: List<Int?> = rowOrColumn.map { cell ->
        this.get(cell)
    }

    println("==============================")
    println(" > current")
    println(" > " + values)

    val merged = values.moveAndMergeEqual { value -> value + value }
    println(" > merged")
    println(" > " + merged)

    for (index in 0..rowOrColumn.size-1) {
        if (index <= merged.size-1) {
            val cell = rowOrColumn[index]
            val value =  merged[index]
            this.set(cell, value)
        } else {
            val cell = rowOrColumn[index]
            this.set(cell, null)
        }
    }

    val valuesMoved = merged.isNotEmpty() && merged.size < values.size

    println(" > moved")
    println(" > " + valuesMoved)
    return valuesMoved

}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {

    fun getRows(isReversed: Boolean): List<List<Cell>> {
        val rows = mutableListOf<List<Cell>>()
        for (x in 1..4) {
            val row: List<Cell> = this.getRow(x, 1..4)
            if (isReversed) rows.add(row.reversed())
            else rows.add(row)
        }
        return rows
    }

    fun getColumns(isReversed: Boolean): List<List<Cell>> {
        val rows = mutableListOf<List<Cell>>()
        for (y in 1..4) {
            val cells: List<Cell> = this.getColumn(1..4, y)
            if (isReversed) rows.add(cells.reversed())
            else rows.add(cells)
        }
        return rows
    }

    val results: List<Boolean> = when (direction) {
        Direction.LEFT -> getRows(false)
        Direction.RIGHT -> getRows(true)
        Direction.UP -> getColumns(false)
        Direction.DOWN -> getColumns(true)
    }.map { cells -> this.moveValuesInRowOrColumn(cells) }

    return results.any { result -> result == true }

}

