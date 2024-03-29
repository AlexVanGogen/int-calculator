package edu.ifmo.intexpr.error

import java.lang.Exception
import kotlin.math.max

open class ExceptionWithErrorLocation(message: String) : Exception(message)

class UndefinedVariableException(
    val variableName: String,
    val variableStartOffset: Int,
    val variableEndOffset: Int
) : ExceptionWithErrorLocation(
    StringBuilder()
        .append(" ".repeat(variableStartOffset))
        .appendln("^".repeat(variableEndOffset - variableStartOffset + 1))
        .appendln("Error: undefined variable: $variableName")
        .toString()
)

class LocatedRecognitionException(
    val charPositionInLine: Int,
    message: String
) : ExceptionWithErrorLocation(
    StringBuilder()
        .append(" ".repeat(max(charPositionInLine, 0)))
        .appendln("^")
        .appendln("Syntax error at position ${max(charPositionInLine, 0)}: $message")
        .toString()
)

class DivisionByZeroException(
    val expressionStartOffset: Int,
    val expressionEndOffset: Int
) : ExceptionWithErrorLocation(
    StringBuilder()
        .append(" ".repeat(expressionStartOffset))
        .appendln("^".repeat(expressionEndOffset - expressionStartOffset + 1))
        .appendln("Error: division by zero")
        .toString()
)

class NotAnIntegerException(
    val value: String,
    val numberStartOffset: Int,
    val numberEndOffset: Int
) : ExceptionWithErrorLocation(
    StringBuilder()
        .append(" ".repeat(numberStartOffset))
        .appendln("^".repeat(numberEndOffset - numberStartOffset + 1))
        .appendln("Error: $value is not a 32-bit integer")
        .toString()
)