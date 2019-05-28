package de.kaes3kuch3n.tictactoe.cli

import de.kaes3kuch3n.tictactoe.game.Game
import de.kaes3kuch3n.tictactoe.game.GameState

class CLI(game: Game) {
    private val view = View(game.BOARD_SIZE)
    private val inputHandler = InputHandler(game.BOARD_SIZE)

    init {
        while (game.gameState == GameState.RUNNING) {
            view.drawBoard(game.board)
            view.printTurn(game.activePlayer)

            var validInput = false
            while (!validInput) {
                view.printColumnInput()
                val column: Int
                try {
                    column = inputHandler.getInput()
                } catch (e: InvalidInputException) {
                    view.printInvalidInputError(DrawError.COLUMN)
                    continue
                }

                view.printRowInput()
                val row: Int
                try {
                    row = inputHandler.getInput()
                } catch (e: InvalidInputException) {
                    view.printInvalidInputError(DrawError.ROW)
                    continue
                }

                if (!game.draw(row - 1, column - 1))
                    view.printIllegalDrawError()
                else validInput = true
            }
        }

        view.drawBoard(game.board)
        view.printWinMesage(game.gameState)
    }
}