package cpickl.advent22.day1

object ElvesParser {
    fun parse(lines: List<String>): Elves {
        var currentElfPosition = 1
        var currentElfCalories = mutableListOf<Calories>()
        val elves = mutableListOf<Elf>()
        lines.forEach { line ->
            when (line) {
                "" -> {
                    elves += Elf(currentElfPosition, currentElfCalories)
                    currentElfPosition++
                    currentElfCalories = mutableListOf()
                }
                else -> {
                    currentElfCalories += Calories(line.toInt())
                }
            }
        }
        if (currentElfCalories.isNotEmpty()) {
            elves += Elf(currentElfPosition, currentElfCalories)
        }
        return Elves(elves)
    }
}