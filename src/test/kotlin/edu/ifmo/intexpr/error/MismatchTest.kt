package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class MismatchTest : StringSpec({

    "1 + | error at position 2" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 +") }
        exception.charPositionInLine shouldBe 2
    }

    "1 + + | error at position 4" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + +") }
        exception.charPositionInLine shouldBe 4
    }

    "1 + ( | error at position 4" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + (") }
        exception.charPositionInLine shouldBe 4
    }

    "((1)+(2))+((3)+(4) | error at position 17" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("((1)+(2))+((3)+(4)") }
        exception.charPositionInLine shouldBe 17
    }

    "() | error at position 0" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("()") }
        exception.charPositionInLine shouldBe 0
    }

    "1 + (2 + | error at position 7" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + (2 +") }
        exception.charPositionInLine shouldBe 7
    }
})