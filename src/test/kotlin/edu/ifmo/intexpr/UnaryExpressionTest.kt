package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class UnaryExpressionTest : StringSpec({

    "-+1 == -1" {
        eval("-+1") shouldBe -1
    }

    "----1 == 1" {
        eval("----1") shouldBe 1
    }

    "- + - + - 1 == -1" {
        eval("- + - + - 1") shouldBe -1
    }

    "-(-(++(+1))) == 1" {
        eval("-(-(++(+1)))") shouldBe 1
    }
})