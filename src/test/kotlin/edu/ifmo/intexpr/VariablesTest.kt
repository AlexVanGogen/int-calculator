package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.EvaluationContext.Companion.of as contextOf
import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class VariablesTest : StringSpec({
    "[x -> 2] => x + 1 == 3" {
        eval("x + 1", contextOf("x" to 2)) shouldBe 3
    }

    "[x -> 2, y -> 1] => x + y == 3" {
        eval("x + y", contextOf("x" to 2, "y" to 1)) shouldBe 3
    }

    "[x -> 3, y -> -4] => x*x + 2*x*y + y*y == 1" {
        eval("x*x + 2*x*y + y*y", contextOf("x" to 3, "y" to -4)) shouldBe 1
    }

    "[x -> 1, y -> 2, z -> 3, product -> 6] => x * y * z - product = 0" {
        eval("x * y * z - product", contextOf("x" to 1, "y" to 2, "z" to 3, "product" to 6)) shouldBe 0
    }

    "[a -> 2, b -> 2, c -> 2] => (a - 5 * (-3 - b)) * (1 + c) == 81" {
        eval("(a - 5 * (-3 - b)) * (1 + c)", contextOf("a" to 2, "b" to 2, "c" to 2)) shouldBe 81
    }
})