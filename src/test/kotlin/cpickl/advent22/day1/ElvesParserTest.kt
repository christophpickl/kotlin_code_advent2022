package cpickl.advent22.day1

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ElvesParserTest : StringSpec({
    "When parse empty lines Then return empty" {
        val elves = ElvesParser.parse(emptyList())

        elves shouldBe Elves.empty
    }
    "When parse 1 elf with 1 entry Then return that elf" {
        val elves = ElvesParser.parse(listOf("1"))

        elves shouldBe Elves(Elf(1, 1))
    }
    "When parse 1 elf with 2 entries Then return that elf" {
        val elves = ElvesParser.parse(listOf("1", "2"))

        elves shouldBe Elves(Elf(1, 1, 2))
    }
    "When parse 2 elves Then return both" {
        val elves = ElvesParser.parse(listOf("1", "", "2"))

        elves shouldBe Elves(Elf(1, 1), Elf(2, 2))
    }
})
