package cpickl.advent22.day1

data class Elves(
    val elves: List<Elf>,
) {
    constructor(vararg elves: Elf) : this(elves.toList())

    init {
        require(elves.map { it.position }.distinct().size == elves.size) {
            "Found duplicate positions for elves which is not allowed => ${elves.map { it.position }}"
        }
    }

    companion object {
        private const val DEFAULT_LINE_SEPARATOR = "\n"
        val empty = Elves(emptyList())

        fun byClasspath(path: String, lineSeparator: String = DEFAULT_LINE_SEPARATOR): Elves {
            val resource = Elves::class.java.getResource(path) ?: error("File not found in classpath: [$path]")
            return byString(resource.readText(), lineSeparator)
        }

        fun byString(input: String, lineSeparator: String = DEFAULT_LINE_SEPARATOR): Elves =
            ElvesParser.parse(input.trim().split(lineSeparator))
    }

    fun mostCarrying(): CarryingResult =
        if (elves.isEmpty()) CarryingResult.None
        else {
            val elvesByTotalCaloriesSorted = elves.groupBy { it.totalCalories }.toSortedMap()
            CarryingResult.build(elvesByTotalCaloriesSorted[elvesByTotalCaloriesSorted.lastKey()]!!)
        }

    fun mostThreeCarrying(): Long =
        elves.sortedByDescending { it.totalCalories }.take(3).sumOf { it.totalCalories }
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
