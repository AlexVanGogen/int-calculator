package edu.ifmo.intexpr.lexer

import edu.ifmo.intexpr.error.LocatedRecognitionException
import edu.ifmo.intexpr.parser.IntExprLexer
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.Token

class MismatchedSymbolReportingIntExprLexer(input: CharStream) : IntExprLexer(input) {
    override fun emit(token: Token) {
        when (token.type) {
            MismatchedSymbol -> throw LocatedRecognitionException(
                token.charPositionInLine + 1,
                "invalid symbol '${token.text}'"
            )
        }
        super.emit(token)
    }
}