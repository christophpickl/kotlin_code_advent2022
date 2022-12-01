package cpickl.advent22.day1

fun <T> List<T>.toFirstAndRest() = FirstAndRest(first(), drop(1))

data class FirstAndRest<T>(
    val first: T,
    val rest: List<T>,
) {
    val restIsEmpty = rest.isEmpty()
    fun next() = rest.toFirstAndRest()
}
