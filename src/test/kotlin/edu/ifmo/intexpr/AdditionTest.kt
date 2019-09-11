package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class AdditionTest : StringSpec({
    "1 + 1 == 2" {
        ExpressionEvaluator.eval("1 + 1") shouldBe 2
    }

    "-1 + 1 == 0" {
        ExpressionEvaluator.eval("-1 + 1") shouldBe 0
    }

    "1 + -1 == 0" {
        ExpressionEvaluator.eval("1 + -1") shouldBe 0
    }

    "-1+1 == 0" {
        ExpressionEvaluator.eval("-1 + 1") shouldBe 0
    }

    "1+-1 == 0" {
        ExpressionEvaluator.eval("1 + -1") shouldBe 0
    }

    "1 - 1 == 0" {
        ExpressionEvaluator.eval("1 - 1") shouldBe 0
    }

    "1 - -1 == 2" {
        ExpressionEvaluator.eval("1 - -1") shouldBe 2
    }

    "-1 - 1 == -2" {
        ExpressionEvaluator.eval("-1 - 1") shouldBe -2
    }

    "---1 - ---1 == 0" {
        ExpressionEvaluator.eval("---1 - ---1") shouldBe 0
    }

    "1 - 2 + 3 - 4 + 5 - 6 == -3" {
        ExpressionEvaluator.eval("1 - 2 + 3 - 4 + 5 - 6") shouldBe -3
    }
})