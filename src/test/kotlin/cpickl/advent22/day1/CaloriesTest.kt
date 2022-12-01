package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CaloriesTest : StringSpec({
    "When instantiate with negative number Then throw" {
        shouldThrow<Exception> {
            Calories(-1)
        }
    }
})
