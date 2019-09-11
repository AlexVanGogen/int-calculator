package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator

object Cli {
    const val PROMPT = ">>>"
    const val EXIT_MESSAGE = "exit"

    fun run() {
        while (true) {
            print("$PROMPT ")
            val expressionString = readLine() ?: continue
            if (expressionString == EXIT_MESSAGE) break
            println(ExpressionEvaluator.eval(expressionString))
        }
    }
}

fun main() {
    Cli.run()
}