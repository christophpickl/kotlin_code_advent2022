package cpickl.advent22.day1

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next

fun Arb.Companion.calories() = arbitrary {
    Calories(
        value = int(min = 0, max = 1_000).next(),
    )
}

fun Arb.Companion.elf() = arbitrary {
    Elf(
        position = int(min = 1).next(),
        calories = list(calories()).next(),
    )
}
