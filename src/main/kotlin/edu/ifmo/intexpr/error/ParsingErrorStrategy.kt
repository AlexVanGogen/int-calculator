package edu.ifmo.intexpr.error

import org.antlr.v4.runtime.*

class ParsingErrorStrategy : DefaultErrorStrategy() {

    override fun recover(recognizer: Parser, recognitionException: RecognitionException) {
        throw recognitionException
    }

    override fun reportMissingToken(recognizer: Parser) {
        val token = recognizer.currentToken
        val message = ("expected one of ${recognizer.expectedTokens.toString(recognizer.vocabulary)} " +
                "but ${token.text} was found")
        throw LocatedRecognitionException(token.charPositionInLine - 1, message)
    }

    override fun reportNoViableAlternative(recognizer: Parser, noViableAltException: NoViableAltException) {
        val token = noViableAltException.offendingToken
        val message = "no viable alternative for symbol: ${getTokenErrorDisplay(token)}"
        val recognitionException = LocatedRecognitionException(token.charPositionInLine - 1, message)
        recognitionException.initCause(noViableAltException)
        throw recognitionException
    }

    override fun reportInputMismatch(recognizer: Parser, inputMismatchException: InputMismatchException) {
        val token = inputMismatchException.offendingToken
        val message = "mismatched symbol: ${getTokenErrorDisplay(token)}"
        val recognitionException = LocatedRecognitionException(token.charPositionInLine - 1, message)
        recognitionException.initCause(inputMismatchException)
        throw recognitionException
    }

    override fun reportUnwantedToken(recognizer: Parser) {
        val token = recognizer.currentToken
        val message = ("expected one of ${recognizer.expectedTokens.toString(recognizer.vocabulary)} " +
                "but ${token.text} was found")
        throw LocatedRecognitionException(token.charPositionInLine - 1, message)
    }

    override fun reportError(recognizer: Parser, recognitionException: RecognitionException) {
        beginErrorCondition(recognizer)
        when (recognitionException) {
            is NoViableAltException -> reportNoViableAlternative(recognizer, recognitionException)
            is InputMismatchException -> reportInputMismatch(recognizer, recognitionException)
            is FailedPredicateException -> reportFailedPredicate(recognizer, recognitionException)
            else -> reportUnwantedToken(recognizer)
        }
    }
}