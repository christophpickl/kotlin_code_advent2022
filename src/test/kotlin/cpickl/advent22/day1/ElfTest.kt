package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next

class ElfTest : StringSpec() {

    private val anyPosition = Arb.int().next()

    init {
        "When calculate total calories Then return sum" {
            forAll(
                table(
                    headers("calories", "total"),
                    row(emptyList(), 0),
                    row(listOf(100), 1001),
                    row(listOf(100, 200), 300),
                )
            ) { calories, total ->
                elfWithCalories(calories).totalCalories shouldBe total
            }
        }

        "When instantiate with invalid position Then throw" {
            shouldThrow<Exception> {
                Arb.elf().next().copy(position = 0)
            }
        }
    }

    private fun elfWithCalories(calories: List<Int>) = Elf(anyPosition, calories.map { Calories(it) })
}
