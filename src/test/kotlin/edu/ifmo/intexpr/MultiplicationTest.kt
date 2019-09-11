package edu.ifmo.intexpr

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MultiplicationTest : StringSpec({
    "1 * 1 == 1" {
        eval("1 * 1") shouldBe 1
    }

    "7 * 8 == 56" {
        eval("7 * 8") shouldBe 56
    }

    "91410 * 1 == 91410" {
        eval("91410 * 1") shouldBe 91410
    }

    "91410 * -1 == -91410" {
        eval("91410 * -1") shouldBe -91410
    }

    "-91410 * 1 == -91410" {
        eval("-91410 * 1") shouldBe -91410
    }

    "-91410 * -1 == 91410" {
        eval("-91410 * -1") shouldBe 91410
    }

    "-7 * -8 == 56" {
        eval("-7 * -8") shouldBe 56
    }

    "-7 * 0 == 0" {
        eval("-7 * 0") shouldBe 0
    }

    "0 * 8 == 0" {
        eval("0 * 8") shouldBe 0
    }

    "1 / 1 == 1" {
        eval("1 / 1") shouldBe 1
    }

    "56 / 7 == 8" {
        eval("56 / 7") shouldBe 8
    }

    "55 / 7 == 7" {
        eval("55 / 7") shouldBe 7
    }

    "91410 / 1 == 91410" {
        eval("91410 / 1") shouldBe 91410
    }

    "91410 / -1 == -91410" {
        eval("91410 / -1") shouldBe -91410
    }

    "-91410 / 1 == -91410" {
        eval("-91410 / 1") shouldBe -91410
    }

    "-91410 / -1 == 91410" {
        eval("-91410 / -1") shouldBe 91410
    }

    "-55 / -7 == 7" {
        eval("-55 / -7") shouldBe 7
    }

    "0 / -7 == 0" {
        eval("0 / -7") shouldBe 0
    }
})