package de.kaes3kuch3n.tictactoe.cli.exceptions

class InvalidInputException(message: String?): Exception(message) {
    constructor() : this(null)
}