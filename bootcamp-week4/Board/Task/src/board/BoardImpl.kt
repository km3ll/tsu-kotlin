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
        val isReversed = jRange.first >= jRange.last
        println("isReversed: $isReversed")
        return if (jRange.first >= jRange.last) {
            cells.filter { it.i == i && it.j in jRange}.reversed()
        } else {
            cells.filter { it.i == i && it.j in jRange}
        }

    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val isReversed = iRange.first >= iRange.last
        println("isReversed: $isReversed")
        return if (isReversed) {
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



fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)


fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

