package cpickl.advent22.day1

object SolutionApp {

    @JvmStatic
    fun main(args: Array<String>) {
        println(loadElves().mostCarrying().toPrintString())
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
