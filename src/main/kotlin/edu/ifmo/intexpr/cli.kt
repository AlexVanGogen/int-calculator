package edu.ifmo.intexpr

import edu.ifmo.intexpr.error.ExceptionWithErrorLocation
import edu.ifmo.intexpr.error.LocatedRecognitionException
import edu.ifmo.intexpr.error.UndefinedVariableException
import edu.ifmo.intexpr.eval.EvaluationContext
import edu.ifmo.intexpr.eval.ExpressionEvaluator

object Cli {
    const val PROMPT = ">>>"
    const val EXIT_MESSAGE = "exit"

    fun run() {
        val context = EvaluationContext.of<Int>()

        while (true) {
            print("$PROMPT ")
            val expressionString = readLine() ?: continue
            if (expressionString == EXIT_MESSAGE) break

            try {
                ExpressionEvaluator.eval(expressionString, context)?.let(::println)
            } catch (e: LocatedRecognitionException) {
                println(" ".repeat(PROMPT.length + e.charPositionInLine) + "^")
                println(e.message)
            } catch (e: ExceptionWithErrorLocation) {
                println(" ".repeat(PROMPT.length + 1) + e.message)
            }
        }
    }
}

fun main() {
    Cli.run()
}