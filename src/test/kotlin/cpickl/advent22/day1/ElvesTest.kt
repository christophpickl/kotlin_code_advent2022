package cpickl.advent22.day1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.property.Arb
import io.kotest.property.arbitrary.next

class ElvesTest : DescribeSpec() {

    private val notExistingClasspath = "/not_existing.txt"
    private val caloriesInt = Arb.calories().next().value
    private val elf = Arb.elf().next()
    private val samePosition = Position(1)

    init {
        describe("When parsing") {
            it("by classpath Then read content properly") {
                val elves = Elves.byClasspath("/cpickl/advent22/day1/single_elf_with_100_calories.txt")

                elves shouldBe Elves(Elf(1, 100))
            }
            it("by invalid classpath Then throw") {
                shouldThrow<Exception> {
                    Elves.byClasspath(notExistingClasspath)
                }.message shouldContain notExistingClasspath
            }
            it("without trailing line Then return 1 elf") {
                val elves = Elves.byString("$caloriesInt")

                elves shouldBe Elves(Elf(1, caloriesInt))
            }
            it("with trailing line Then return 1 elf nevertheless") {
                val elves = Elves.byString("$caloriesInt\n")

                elves shouldBe Elves(Elf(1, caloriesInt))
            }
        }

        describe("When instantiating") {
            it("with duplicate positions Then throw") {
                shouldThrow<Exception> {
                    Elves(elf.copy(position = samePosition), elf.copy(position = samePosition))
                }
            }
        }

        describe("When calculate most carrying") {
            it("Given no elves Then return none") {
                Elves(emptyList()).mostCarrying() shouldBe CarryingResult.None
            }
            it("Given a single elf Then return it") {
                Elves(elf).mostCarrying().shouldBeInstanceOf<CarryingResult.One>().elf shouldBe elf
            }
            it("Given two elves Then return one with more calories") {
                val elf1 = Elf(1, 1)
                val elf2 = Elf(2, 2)
                Elves(elf1, elf2).mostCarrying().shouldBeInstanceOf<CarryingResult.One>().elf shouldBe elf2
            }
            it("Given two elves with same calories Then return null") {
                val elfA = Elf(1, 1)
                val elfB = Elf(2, 1)
                val elfX = Elf(3, 0)
                Elves(elfA, elfB, elfX).mostCarrying().shouldBeInstanceOf<CarryingResult.Some>().elves shouldBe listOf(
                    elfA,
                    elfB
                )
            }
        }

        describe("When calculate top 3 carrying") {
            it("Given different elves Then calculate properly") {
                forAll(
                    table(
                        headers("elves", "total"),
                        row({ }, 0L),
                        row({ elf(1) }, 1L),
                        row({ elf(1).elf(2) }, 3L),
                        row({ elf(1).elf(2).elf(3) }, 6L),
                        row({ elf(1).elf(10).elf(20).elf(30) }, 60L),
                        row({ elf(1).elf(10).elf(10).elf(10) }, 30L),
                        row({ elf(1).elf(20).elf(20).elf(10) }, 50L),
                    )
                ) { prepare: ElfBuilder.() -> Unit, total: Long ->
                    Elves(ElfBuilder().apply { prepare() }.build()).mostThreeCarrying() shouldBe total
                }
            }
        }
    }
}
