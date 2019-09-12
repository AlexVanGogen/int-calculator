package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.EvaluationContext.Companion.of as contextOf
import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class DivisionByZeroTest : StringSpec({

    "1 / 0 | error at position 4-4" {
        val exception = shouldThrow<DivisionByZeroException> { eval("1 / 0") }
        exception.expressionStartOffset shouldBe 4
        exception.expressionEndOffset shouldBe 4
    }

    "5 % (2 - 2) | error at position 4-10" {
        val exception = shouldThrow<DivisionByZeroException> { eval("5 % (2 - 2)") }
        exception.expressionStartOffset shouldBe 4
        exception.expressionEndOffset shouldBe 10
    }

    "1 / 2 / (3 / 4) | error at position 8-14" {
        val exception = shouldThrow<DivisionByZeroException> { eval("1 / 2 / (3 / 4)") }
        exception.expressionStartOffset shouldBe 8
        exception.expressionEndOffset shouldBe 14
    }

    "[x -> 0] => 18 % x | error at position 5-5" {
        val exception = shouldThrow<DivisionByZeroException> { eval("18 % x", contextOf("x" to 0)) }
        exception.expressionStartOffset shouldBe 5
        exception.expressionEndOffset shouldBe 5
    }

    "[x -> 5, y -> -5] => 3 + (2 / (x + y)) | error at position 9-15" {
        val exception = shouldThrow<DivisionByZeroException> { eval("3 + (2 / (x + y))", contextOf("x" to 5, "y" to -5)) }
        exception.expressionStartOffset shouldBe 9
        exception.expressionEndOffset shouldBe 15
    }
})