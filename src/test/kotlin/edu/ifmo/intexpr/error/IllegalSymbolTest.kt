package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class IllegalSymbolTest : StringSpec({
    "1? | error at position 2" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1?") }
        exception.charPositionInLine shouldBe 2
    }

    "1 ? | error at position 3" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 ?") }
        exception.charPositionInLine shouldBe 3
    }

    "1 + ? | error at position 5" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + ?") }
        exception.charPositionInLine shouldBe 5
    }

    "1!!!! | error at position 2" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1!!!!") }
        exception.charPositionInLine shouldBe 2
    }

    "# - 2 | error at position 1" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("# - 2") }
        exception.charPositionInLine shouldBe 1
    }
})