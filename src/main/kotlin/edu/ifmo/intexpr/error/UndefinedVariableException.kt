package edu.ifmo.intexpr.error

import java.lang.Exception

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