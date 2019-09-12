package edu.ifmo.intexpr.eval

import edu.ifmo.intexpr.error.ParsingErrorStrategy
import edu.ifmo.intexpr.lexer.MismatchedSymbolReportingIntExprLexer
import edu.ifmo.intexpr.parser.IntExprParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

object ExpressionEvaluator {

    fun eval(expression: String, context: EvaluationContext<Int> = EvaluationContext.of()): Int? {
        val lexer = MismatchedSymbolReportingIntExprLexer(CharStreams.fromString(expression))
        val parser = IntExprParser(CommonTokenStream(lexer))
        parser.errorHandler = ParsingErrorStrategy()
        return parser.line().accept(EvaluationVisitor(context))
    }
}