package cpickl.advent22.day1

@JvmInline
value class Calories(val value: Int) {
    init {
        require(value >= 0) {
            "Calories must be >= 0 but was: $value"
        }
    }

    companion object {
        fun parse(string: String): Calories =
            string.toIntOrNull()?.let { Calories(it) } ?: error("Invalid calories: [$string]")
    }

    override fun toString() = value.toString()
}
