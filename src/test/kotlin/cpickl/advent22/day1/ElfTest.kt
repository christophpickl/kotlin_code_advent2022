package cpickl.advent22.day1

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.next

class ElfTest : StringSpec() {

    private val anyPosition = Arb.position().next()

    init {
        "When calculate total calories Then return sum" {
            forAll(
                table(
                    headers("calories", "total"),
                    row(emptyList(), 0),
                    row(listOf(1), 1),
                    row(listOf(1, 2), 3),
                )
            ) { calories, total ->
                elfWithCalories(calories).totalCalories shouldBe total
            }
        }
    }

    private fun elfWithCalories(calories: List<Int>) = Elf(anyPosition, calories.map { Calories(it) })
}
