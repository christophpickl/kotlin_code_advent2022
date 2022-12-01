package cpickl.advent22.day1

data class Elves(
    val elves: List<Elf>,
) {
    constructor(vararg elves: Elf) : this(elves.toList())

    init {
        require(elves.map { it.position }.distinct().size == elves.size)
    }

    companion object {
        private const val DEFAULT_LINE_SEPARATOR = "\n"
        val empty = Elves(emptyList())

        fun byClasspath(path: String): Elves {
            val resource = Elves::class.java.getResource(path) ?: error("File not found in classpath: [$path]")
            return byString(resource.readText())
        }

        fun byString(input: String, lineSeparator: String = DEFAULT_LINE_SEPARATOR): Elves =
            byLines(input.trim().split(lineSeparator))

        private fun byLines(lines: List<String>): Elves =
            ElvesParser.parse(lines)
    }

    fun mostCarrying(): CarryingResult =
        if (elves.isEmpty()) CarryingResult.None
        else {
            val elvesByTotalCaloriesSorted = elves.groupBy { it.totalCalories }.toSortedMap()
            val elves = elvesByTotalCaloriesSorted[elvesByTotalCaloriesSorted.lastKey()]!!
            CarryingResult.build(elves)
        }

    fun mostThreeCarrying(): Long = 0L
}

sealed interface CarryingResult {
    object None : CarryingResult
    class One(val elf: Elf) : CarryingResult
    class Some(val elves: List<Elf>) : CarryingResult {
        init {
            require(elves.isNotEmpty())
        }
    }

    companion object {
        fun build(elves: List<Elf>): CarryingResult =
            when {
                elves.isEmpty() -> None
                elves.size == 1 -> One(elves.first())
                else -> Some(elves)
            }
    }
}
