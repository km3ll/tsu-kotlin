package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import games.game.GameImpl

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameImpl(initializer)

