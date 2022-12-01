package cpickl.advent22.day1

data class Elves(
    val elves: Set<Elf>,
) {
    companion object {
        private const val DEFAULT_LINE_SEPARATOR = "\n"

        fun byClasspath(path: String): Elves {
            val resource = Elves::class.java.getResource(path) ?: error("File not found in classpath: [$path]")
            return byString(resource.readText())
        }

        fun byString(input: String, lineSeparator: String = DEFAULT_LINE_SEPARATOR): Elves =
            byLines(input.split(lineSeparator))

        private fun byLines(lines: List<String>): Elves {
            var currentElfPosition = 1
            var currentElfCalories = mutableSetOf<Calories>()
            val elves = mutableSetOf<Elf>()
            lines.forEach { line ->
                println("parsing line: [$line]")
                when (line) {
                    "" -> {
                        elves += Elf(currentElfPosition, currentElfCalories)
                        currentElfPosition++
                        currentElfCalories = mutableSetOf()
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
}

@JvmInline
value class Calories(val value: Int) {
    override fun toString() = value.toString()
}

data class Elf(
    val position: Int,
    val calories: Set<Calories>,
)