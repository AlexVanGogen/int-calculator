package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class IdentityTest : StringSpec({

    "1 == 1" {
        eval("1") shouldBe 1
    }

    "-42 == -42" {
        eval("-42") shouldBe -42
    }

    "-    81 == -81" {
        eval("-    81") shouldBe -81
    }
})

