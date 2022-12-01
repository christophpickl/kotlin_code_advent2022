package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class PositionTest : StringSpec({
    "When instantiate with invalid number Then throw" {
        shouldThrow<Exception> {
            Position(0)
        }.message shouldContain "0"
    }
    "When get next Then increment value" {
        Position(1).next().value shouldBe 2
    }
})
