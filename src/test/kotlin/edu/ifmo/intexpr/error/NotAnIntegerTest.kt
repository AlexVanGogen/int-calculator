package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class NotAnIntegerTest : StringSpec({

    "584578934589358934 | not an integer at position 0-17" {
        val exception = shouldThrow<NotAnIntegerException> { eval("584578934589358934") }
        exception.value shouldBe "584578934589358934"
        exception.numberStartOffset shouldBe 0
        exception.numberEndOffset shouldBe 17
    }

    "2147483648 | not an integer at position 0-9" {
        val exception = shouldThrow<NotAnIntegerException> { eval("2147483648") }
        exception.value shouldBe "2147483648"
        exception.numberStartOffset shouldBe 0
        exception.numberEndOffset shouldBe 9
    }

    "1 / 2147483648 | not an integer at position 4-13" {
        val exception = shouldThrow<NotAnIntegerException> { eval("1 / 2147483648") }
        exception.value shouldBe "2147483648"
        exception.numberStartOffset shouldBe 4
        exception.numberEndOffset shouldBe 13
    }

    "1 / -2147483649 | not an integer at position 5-14" {
        val exception = shouldThrow<NotAnIntegerException> { eval("1 / -2147483649") }
        exception.value shouldBe "2147483649"
        exception.numberStartOffset shouldBe 5
        exception.numberEndOffset shouldBe 14
    }

    "-2147483648 is ok" {
        shouldNotThrow<NotAnIntegerException> { eval("-2147483648") }
    }

    "2147483647 is ok" {
        shouldNotThrow<NotAnIntegerException> { eval("2147483647") }
    }
})