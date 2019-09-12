package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.EvaluationContext
import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class LetStatementTest : StringSpec({

    "let x = 2; x + 1 == 3" {
        val context = EvaluationContext.of<Int>()
        eval("let x = 2", context)
        context.getValue("x") shouldBe 2

        eval("x + 1", context) shouldBe 3
    }

    "let x = 2; let y = 1; x + y == 3; y + x == 3" {
        val context = EvaluationContext.of<Int>()
        eval("let x = 2", context)
        context.getValue("x") shouldBe 2

        eval("let y = 1", context)
        context.getValue("y") shouldBe 1

        eval("x + y", context) shouldBe 3
        eval("y + x", context) shouldBe 3
    }

    "let x = 2; let y = x + 5; x * y == 14" {
        val context = EvaluationContext.of<Int>()
        eval("let x = 2", context)
        context.getValue("x") shouldBe 2

        eval("let y = x + 5", context)
        context.getValue("y") shouldBe 7

        eval("x * y", context) shouldBe 14
    }

    "let x = 2; let x = x * x; let x = x * x; x * x == 256" {
        val context = EvaluationContext.of<Int>()
        eval("let x = 2", context)
        context.getValue("x") shouldBe 2

        eval("let x = x * x", context)
        context.getValue("x") shouldBe 4

        eval("let x = x * x", context)
        context.getValue("x") shouldBe 16

        eval("x * x", context) shouldBe 256
    }

    "let x = 2; let y = -3; x * y == -6; let x = 3; x * y == -9" {
        val context = EvaluationContext.of<Int>()
        eval("let x = 2", context)
        context.getValue("x") shouldBe 2

        eval("let y = -3", context)
        eval("x * y", context) shouldBe -6

        eval("let x = 3", context)
        context.getValue("x") shouldBe 3

        eval("x * y", context) shouldBe -9
    }
})