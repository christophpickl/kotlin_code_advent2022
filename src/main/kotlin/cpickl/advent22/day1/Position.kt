package cpickl.advent22.day1

@JvmInline
value class Position(val value: Int) {
    init {
        require(value >= 1) {
            "Position must be >= 1 but was: $value"
        }
    }

    companion object {
        val first = Position(1)
    }

    fun next() = Position(value + 1)

    override fun toString() = value.toString()
}
