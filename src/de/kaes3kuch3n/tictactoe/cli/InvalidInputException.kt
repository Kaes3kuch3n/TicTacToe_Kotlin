package de.kaes3kuch3n.tictactoe.cli

class InvalidInputException(message: String?): Exception(message) {
    constructor() : this(null)
}