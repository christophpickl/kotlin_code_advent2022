package cpickl.advent22.day1

object Day1App {

    @JvmStatic
    fun main(args: Array<String>) {
        part2()
    }

    fun part1() {
        println(loadElves().mostCarrying().toPrintString())
        // Elf on position #209 has 74198 calories.
    }

    fun part2() {
        println("The top 3 carrying elves carry: ${loadElves().mostThreeCarrying()}")
        // The top 3 carrying elves carry: 209914
    }

    private fun loadElves(): Elves =
        Elves.byClasspath("/cpickl/advent22/day1/given_input.txt")

    private fun CarryingResult.toPrintString(): String = when (this) {
        CarryingResult.None -> "No elf given to calculate top calories."
        is CarryingResult.One -> elf.toPrintString()
        is CarryingResult.Some -> {
            "Multiple elves with same top calories found:\n" +
                    elves.joinToString("\n") {
                        "- ${it.toPrintString()}"
                    }
        }
    }

    private fun Elf.toPrintString() =
        "Elf on position #$position has $totalCalories calories."
}
