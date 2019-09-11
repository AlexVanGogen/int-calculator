package edu.ifmo.intexpr.eval

import edu.ifmo.intexpr.parser.IntExprLexer
import edu.ifmo.intexpr.parser.IntExprParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

object ExpressionEvaluator {

    fun eval(expression: String): Int {
        val lexer = IntExprLexer(CharStreams.fromString(expression))
        val parser = IntExprParser(CommonTokenStream(lexer))
        return parser.line().accept(EvaluationVisitor())
    }
}