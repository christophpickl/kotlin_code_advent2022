package cpickl.advent22.day1

object ElvesParser {

    private const val elfDelimiter = ""
    private val emptyCalories = emptyList<Calories>()

    fun parse(lines: List<String>): Elves {
        if (lines.isEmpty()) return Elves.empty
        return Elves(parse(1, emptyCalories, lines.toFirstAndRest(), emptyList()))
    }

    private fun parse(
        position: Int,
        calories: List<Calories>,
        lines: FirstAndRest<String>,
        result: List<Elf>
    ): List<Elf> =
        if (lines.first == elfDelimiter) {
            parse(position + 1, emptyCalories, lines.next(), result.plus(Elf(position, calories)))
        } else {
            val newCalories = calories.plus(Calories.parse(lines.first))
            if (lines.restIsEmpty) {
                result.plus(Elf(position, newCalories))
            } else {
                parse(position, newCalories, lines.next(), result)
            }
        }
}

fun <T> List<T>.toFirstAndRest() = FirstAndRest(first(), drop(1))

data class FirstAndRest<T>(
    val first: T,
    val rest: List<T>,
) {
    val restIsEmpty = rest.isEmpty()
    fun next() = rest.toFirstAndRest()
}
