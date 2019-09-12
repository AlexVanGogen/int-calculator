package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.EvaluationContext.Companion.of as contextOf
import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class UndefinedVariableTest : StringSpec({

    "x + 5 | x on position 0-0 is undefined" {
        val exception = shouldThrow<UndefinedVariableException> { eval("x + 5") }
        exception.variableName shouldBe "x"
        exception.variableStartOffset shouldBe 0
        exception.variableEndOffset shouldBe 0
    }

    "5 + y | y on position 4-4 is undefined" {
        val exception = shouldThrow<UndefinedVariableException> { eval("5 + y") }
        exception.variableName shouldBe "y"
        exception.variableStartOffset shouldBe 4
        exception.variableEndOffset shouldBe 4
    }

    "let z = z + 1 | z on position 8-8 is undefined" {
        val exception = shouldThrow<UndefinedVariableException> { eval("let z = z + 1") }
        exception.variableName shouldBe "z"
        exception.variableStartOffset shouldBe 8
        exception.variableEndOffset shouldBe 8
    }

    "[first -> 5] => first + second | second on position 8-13 is undefined" {
        val exception = shouldThrow<UndefinedVariableException> { eval("first + second", contextOf("first" to 5)) }
        exception.variableName shouldBe "second"
        exception.variableStartOffset shouldBe 8
        exception.variableEndOffset shouldBe 13
    }

    "let first = 5; first + second | second on position 8-13 is undefined" {
        val context = contextOf<Int>()
        eval("let first = 5", context)
        val exception = shouldThrow<UndefinedVariableException> { eval("first + second", context) }
        exception.variableName shouldBe "second"
        exception.variableStartOffset shouldBe 8
        exception.variableEndOffset shouldBe 13
    }

    "[a -> 1] => let b = a + b | b on position 12-12 is undefined" {
        val exception = shouldThrow<UndefinedVariableException> { eval("let b = a + b", contextOf("a" to 1)) }
        exception.variableName shouldBe "b"
        exception.variableStartOffset shouldBe 12
        exception.variableEndOffset shouldBe 12
    }

    "let a = 1; let b = a + b | b on position 12-12 is undefined" {
        val context = contextOf<Int>()
        eval("let a = 1", context)
        val exception = shouldThrow<UndefinedVariableException> { eval("let b = a + b", context) }
        exception.variableName shouldBe "b"
        exception.variableStartOffset shouldBe 12
        exception.variableEndOffset shouldBe 12
    }
})