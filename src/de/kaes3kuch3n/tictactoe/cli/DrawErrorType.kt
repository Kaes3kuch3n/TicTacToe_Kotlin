package de.kaes3kuch3n.tictactoe.cli

enum class DrawErrorType(private val error: String) {
    COLUMN("Column"), ROW("Row");

    override fun toString(): String {
        return error
    }
}