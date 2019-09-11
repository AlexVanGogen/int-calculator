package edu.ifmo.intexpr

import edu.ifmo.intexpr.error.LocatedRecognitionException
import edu.ifmo.intexpr.eval.ExpressionEvaluator

object Cli {
    const val PROMPT = ">>>"
    const val EXIT_MESSAGE = "exit"

    fun run() {
        while (true) {
            print("$PROMPT ")
            val expressionString = readLine() ?: continue
            if (expressionString == EXIT_MESSAGE) break
            try {
                println(ExpressionEvaluator.eval(expressionString))
            } catch (e: LocatedRecognitionException) {
                println(" ".repeat(PROMPT.length + e.charPositionInLine) + "^")
                println(e.message)
            }
        }
    }
}

fun main() {
    Cli.run()
}