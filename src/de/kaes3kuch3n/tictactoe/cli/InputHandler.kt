package de.kaes3kuch3n.tictactoe.cli

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.NumberFormatException

class InputHandler(private val boardSize: Int) {
    private val reader = BufferedReader(InputStreamReader(System.`in`))

    fun getInput(): Int {
        val input = inputToInt(reader.readLine())
        if (input == -1)
            throw InvalidInputException()
        return input
    }

    private fun inputToInt(input: String): Int {
        try {
            val number = input.toInt()
            if (number in 1..boardSize)
                return number
        } catch (e: NumberFormatException) { }
        return -1
    }
}