package edu.ifmo.intexpr.error

import edu.ifmo.intexpr.eval.ExpressionEvaluator.eval
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class IllegalSymbolTest : StringSpec({
    "1? | error at position 1" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1?") }
        exception.charPositionInLine shouldBe 1
    }

    "1 ? | error at position 2" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 ?") }
        exception.charPositionInLine shouldBe 2
    }

    "1 + ? | error at position 4" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1 + ?") }
        exception.charPositionInLine shouldBe 4
    }

    "1!!!! | error at position 1" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("1!!!!") }
        exception.charPositionInLine shouldBe 1
    }

    "# - 2 | error at position 0" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("# - 2") }
        exception.charPositionInLine shouldBe 0
    }

    "&#@$&*#@*(&%*()@#*$#(@##)@$#@)) | error at position 0" {
        val exception = shouldThrow<LocatedRecognitionException> { eval("&#@\$&*#@*(&%*()@#*\$#(@##)@\$#@))") }
        exception.charPositionInLine shouldBe 0
    }
})