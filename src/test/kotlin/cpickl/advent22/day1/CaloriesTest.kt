package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class CaloriesTest : DescribeSpec({
    describe("When instantiate") {
        it("with valid number Then return it") {
            Calories(0).value shouldBe 0
        }
        it(" with invalid number Then throw") {
            shouldThrow<Exception> {
                Calories(-1)
            }
        }
    }
    describe("When parse") {
        it("with valid string Then return it") {
            Calories.parse("1").value shouldBe 1
        }
        it("with invalid string Then throw") {
            shouldThrow<Exception> {
                Calories.parse("x")
            }.message shouldContain "x"
        }
    }
})
