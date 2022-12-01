package cpickl.advent22.day1

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next

class ElfBuilder {
    private var position = Position.first
    private val elves = mutableListOf<Elf>()
    fun elf(vararg calories: Int) = apply {
        elves += Elf(position, calories.map { Calories(it) })
        position = position.next()
    }

    fun build(): List<Elf> = elves
}

fun Arb.Companion.calories() = arbitrary {
    Calories(
        value = int(min = 0, max = 1_000).next(),
    )
}

fun Arb.Companion.position() = arbitrary {
    Position(
        value = int(min = 1, max = 1_000).next(),
    )
}

fun Arb.Companion.elf() = arbitrary {
    Elf(
        position = position().next(),
        calories = list(calories()).next(),
    )
}
