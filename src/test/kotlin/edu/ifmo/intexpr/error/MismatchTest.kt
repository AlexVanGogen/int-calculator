package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class MismatchTest : StringSpec({

    "1 + | error at position 3" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 +") }
        exception.charPositionInLine shouldBe 3
    }

    "1 + + | error at position 5" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + +") }
        exception.charPositionInLine shouldBe 5
    }

    "1 + ( | error at position 5" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + (") }
        exception.charPositionInLine shouldBe 5
    }

    "((1)+(2))+((3)+(4) | error at position 18" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("((1)+(2))+((3)+(4)") }
        exception.charPositionInLine shouldBe 18
    }

    "() | error at position 1" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("()") }
        exception.charPositionInLine shouldBe 1
    }

    "1 + (2 + | error at position 8" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + (2 +") }
        exception.charPositionInLine shouldBe 8
    }
})