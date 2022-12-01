package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class Day1Test : StringSpec({

    val notExistingClasspath = "/not_existing.txt"
    val calories = Calories(100)

    "When parse by classpath Then read content properly" {
        val elves = Elves.byClasspath("/cpickl/advent22/day1/simple_input.txt")

        elves.elves shouldBe setOf(Elf(1, setOf(Calories(100))))
    }

    "When parse by invalid classpath Then fail" {
        shouldThrow<Exception> {
            Elves.byClasspath(notExistingClasspath)
        }.message shouldContain notExistingClasspath
    }

    "When parse with trailing line Then read content properly" {
        val elves = Elves.byString("$calories\n")

        elves.elves shouldBe setOf(Elf(1, setOf(calories)))
    }

    "When parse single line without trailing line Then read content properly" {
        val elves = Elves.byString("$calories")

        elves.elves shouldBe setOf(Elf(1, setOf(calories)))
    }
})
