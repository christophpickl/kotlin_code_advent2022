package cpickl.advent22.day1

data class Elf(
    /** 1 base indexed */
    val position: Position,
    val calories: List<Calories>,
) {
    constructor(position: Int, vararg calories: Int) : this(Position(position), calories.map { Calories(it) })

    val totalCalories: Long = calories.sumOf { it.value.toLong() }
}
