import java.time.Instant

fun main() {
    println(
        generateSequence(Instant.now()) { it.plusSeconds(1) }
            .take(5)
            .toList())
}
