package de.kaes3kuch3n.tictactoe.cli

import de.kaes3kuch3n.tictactoe.game.Field
import de.kaes3kuch3n.tictactoe.game.GameState

class View(private val boardSize: Int) {
    private val turnMessage = "Turn: {player}"
    private val columnInputPrompt = "Column: "
    private val rowInputPrompt = "Row: "
    private val illegalDrawErrorMessage = "This field is already occupied!"
    private val separator = "========================================"
    private val winMessage = "And the winner is Player {player}!"
    private val drawMessage = "The game ended in a draw"

    fun drawBoard(board: Array<Array<Field>>) {
        val convertedBoard = convertBoard(board)

        println()

        for (i in 0 until boardSize - 1) {
            println(rowToPrintString(convertedBoard[i]))
            println("---+".repeat(boardSize - 1) + "---")
        }
        println(rowToPrintString(convertedBoard[boardSize - 1]))

        println()
    }

    fun printTurn(player: Field) {
        when (player) {
            Field.CROSS -> println(playerSpecificString(turnMessage, "Cross"))
            Field.CIRCLE -> println(playerSpecificString(turnMessage, "Circle"))
            else -> throw InvalidStateError("Player can't be ${player.name}")
        }
    }

    fun printColumnInput() {
        print("\n$columnInputPrompt")
    }

    fun printRowInput() {
        print(rowInputPrompt)
    }

    fun printInvalidInputError(error: DrawError) {
        println("Invalid ${error.toString().toLowerCase()} input! $error must be a number between 1 and $boardSize")
    }

    fun printIllegalDrawError() {
        println(illegalDrawErrorMessage)
    }

    fun printWinMesage(endState: GameState) {
        println()
        println(separator)
        println()

        when (endState) {
            GameState.OVER_CROSS -> println(playerSpecificString(winMessage, "Cross"))
            GameState.OVER_CIRCLE -> println(playerSpecificString(winMessage, "Circle"))
            GameState.OVER_DRAW -> println(drawMessage)
            else -> throw Error("The game is still running! Win message can't be printed now")
        }

        println()
        println(separator)
        println()
    }

    private fun rowToPrintString(row: Array<Char>): String = row.joinToString(" | ", " ", " ")

    private fun convertBoard(board: Array<Array<Field>>): Array<Array<Char>> {
        return board.map { row -> row.map { field -> fieldToPlayerChar(field) }.toTypedArray() }.toTypedArray()
    }

    private fun fieldToPlayerChar(field: Field): Char {
        return when (field) {
            Field.CROSS -> 'X'
            Field.CIRCLE -> 'O'
            else -> ' '
        }
    }

    private fun playerSpecificString(string: String, player: String): String {
        return string.replace("{player}", player)
    }
}