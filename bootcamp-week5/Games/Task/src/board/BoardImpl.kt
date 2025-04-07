package board

import board.Direction.*

class SquareBoardImpl(override val width: Int) : SquareBoard {

    private val cells: MutableList<Cell> = mutableListOf<Cell>()

    init {
        for (x in 1..width) {
            for (y in 1..width) {
                val cell = Cell(i = x, j = y)
                println("$cell")
                cells.add(cell)
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        for (cell in cells) {
            if (cell.i == i && cell.j == j) return cell
        }
        return null
    }

    override fun getCell(i: Int, j: Int): Cell {
        for (cell in cells) {
            if (cell.i == i && cell.j == j) return cell
        }
        throw IllegalArgumentException("Incorrect values for i: $i and j: $j")
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return if (jRange.first >= jRange.last) {
            cells.filter { it.i == i && it.j in jRange}.reversed()
        } else {
            cells.filter { it.i == i && it.j in jRange}
        }

    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return if (iRange.first >= iRange.last) {
            cells.filter { it.i in iRange && it.j == j }.reversed()
        } else {
            cells.filter { it.i in iRange && it.j == j }
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val newCell: Pair<Int, Int> = when (direction) {
            UP -> Pair(i-1, j)
            DOWN -> Pair(i+1, j)
            RIGHT -> Pair(i, j+1)
            LEFT -> Pair(i, j-1)
        }
        for (cell in cells) {
            if (cell.i == newCell.first && cell.j == newCell.second) return cell
        }
        return null
    }

}

class GameBoardImpl<T>(override val width: Int) : GameBoard<T> {

    private val cells: MutableList<Cell> = mutableListOf<Cell>()
    private val values: MutableMap<Cell, T?> = mutableMapOf()

    init {
        for (x in 1..width) {
            for (y in 1..width) {
                val cell = Cell(i = x, j = y)
                println("$cell")
                cells.add(cell)
                values.put(cell, null)
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        for (cell in cells) {
            if (cell.i == i && cell.j == j) return cell
        }
        return null
    }

    override fun getCell(i: Int, j: Int): Cell {
        for (cell in cells) {
            if (cell.i == i && cell.j == j) return cell
        }
        throw IllegalArgumentException("Incorrect values for i: $i and j: $j")
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return if (jRange.first >= jRange.last) {
            cells.filter { it.i == i && it.j in jRange}.reversed()
        } else {
            cells.filter { it.i == i && it.j in jRange}
        }

    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return if (iRange.first >= iRange.last) {
            cells.filter { it.i in iRange && it.j == j }.reversed()
        } else {
            cells.filter { it.i in iRange && it.j == j }
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val newCell: Pair<Int, Int> = when (direction) {
            UP -> Pair(i-1, j)
            DOWN -> Pair(i+1, j)
            RIGHT -> Pair(i, j+1)
            LEFT -> Pair(i, j-1)
        }
        for (cell in cells) {
            if (cell.i == newCell.first && cell.j == newCell.second) return cell
        }
        return null
    }

    override fun get(cell: Cell): T? {
        return values[cell]
    }

    override fun set(cell: Cell, value: T?) {
        if (values.contains(cell)) {
            values.put(cell, value)
        }
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return values.filter { it -> predicate(it.value) }.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        for (value in values) {
            if (predicate(value.value)) return value.key
        }
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return values.any { it -> predicate(it.value) }
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return values.all { it -> predicate(it.value) }
    }

}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)
