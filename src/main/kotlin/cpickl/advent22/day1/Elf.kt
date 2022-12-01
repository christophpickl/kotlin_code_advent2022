package cpickl.advent22.day1

data class Elf(
    /** 1 base indexed */
    val position: Int,
    val calories: List<Calories>,
) {
    constructor(position: Int, vararg calories: Int) : this(position, calories.map { Calories(it) })

    init {
        require(position >= 1)
    }

    val totalCalories: Long = calories.sumOf { it.value.toLong() }
}
