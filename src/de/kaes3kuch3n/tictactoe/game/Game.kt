package de.kaes3kuch3n.tictactoe.game

class Game(startingPlayer: Field) {

    @Suppress("PropertyName")
    val BOARD_SIZE: Int = 3

    val board: Array<Array<Field>> = Array(3) { Array(3) { Field.EMPTY } }

    private var setFields: Int = 0

    var gameState: GameState = GameState.RUNNING
        private set

    var activePlayer: Field = startingPlayer
        private set

    constructor() : this(Field.CROSS)

    fun draw(posX: Int, posY: Int): Boolean {
        if (!setField(posX, posY))
            return false

        toggleActivePlayer()
        val winner = checkForWin()

        if (winner == Field.EMPTY && setFields == BOARD_SIZE * BOARD_SIZE)
            gameState = GameState.OVER_DRAW
        else if (winner == Field.CROSS)
            gameState = GameState.OVER_CROSS
        else if (winner == Field.CIRCLE)
            gameState = GameState.OVER_CIRCLE

        return true
    }

    private fun setField(posX: Int, posY: Int): Boolean {
        if (posX < 0 || posX >= BOARD_SIZE || posY < 0 || posY >= BOARD_SIZE)
            return false
        if (board[posX][posY] != Field.EMPTY)
            return false

        board[posX][posY] = activePlayer
        setFields++

        return true
    }

    private fun toggleActivePlayer() {
        activePlayer = if (activePlayer == Field.CROSS) Field.CIRCLE else Field.CROSS
    }

    private fun checkForWin(): Field {
        if (checkForWin(Field.CROSS))
            return Field.CROSS
        if (checkForWin(Field.CIRCLE))
            return Field.CIRCLE
        return Field.EMPTY
    }

    private fun checkForWin(player: Field): Boolean {
        for (i in 0 until BOARD_SIZE) {
            // Check horizontal
            if (board[i].filter { field -> field == player }.count() == BOARD_SIZE)
                return true
            // Check vertical
            if (board.map { row -> row[i] }.filter { field -> field == player }.count() == BOARD_SIZE)
                return true
        }
        // Check diagonal
        if (board.mapIndexed { i, row -> row[i] }.filter { field -> field == player }.count() == BOARD_SIZE)
            return true
        if (board.mapIndexed { i, row -> row[BOARD_SIZE - i - 1] }.filter { field -> field == player}.count() == BOARD_SIZE)
            return true
        // No win detected
        return false
    }
}