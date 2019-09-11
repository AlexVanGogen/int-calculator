package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ComplexExpressionsTest : StringSpec({

    "(8 + 12 / 7 * 3) / 1 + 2 + 3 * 4 - (9 - (1 + 4 % 3)) == 18" {
        eval("(8 + 12 / 7 * 3) / 1 + 2 + 3 * 4 - (9 - (1 + 4 % 3))") shouldBe 18
    }

    "256 /2/2 / 2/ 2 / 2 / 2 == 4" {
        eval("256 / 2 / 2 / 2 / 2 / 2 / 2") shouldBe 4
    }

    "256 / 2 / 2 / 2 / 2 / (2 / 2) == 16" {
        eval("256 / 2 / 2 / 2 / 2 / (2 / 2)") shouldBe 16
    }

    "256 / 2 / (2 / 2) / (2 / 2) / 2 == 64" {
        eval("256 / 2 / (2 / 2) / (2 / 2) / 2") shouldBe 64
    }

    "2 * 2 + 2 * 2 + 2 * (2 + 2) * 2 == 24" {
        eval("2 * 2 + 2 * 2 + 2 * (2 + 2) * 2") shouldBe 24
    }
})