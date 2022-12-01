package cpickl.advent22.day1

@JvmInline
value class Calories(val value: Int) {
    init {
        require(value >= 0)
    }

    override fun toString() = value.toString()
}
