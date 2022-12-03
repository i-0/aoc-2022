package day3

import day3.Parser.parse
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


// ----------------------------------------------------
// data
// ----------------------------------------------------
typealias Rucksack = Pair<String, String>


// ----------------------------------------------------
// algo
// ----------------------------------------------------
object Parser {
    fun parse(input: String): List<Rucksack> =
        input.lines().map {
            val first = it.substring(0, it.length / 2)
            val second = it.substring(it.length / 2, it.length)
            first to second
        }
}

// ----------------------------------------------------
// tests
// ----------------------------------------------------
fun process(rucksacks: List<Rucksack>): Any {
    TODO("Not yet implemented")
}


class Day3Tests {
    @Test
    fun `test first`() {
        val inputSmall = """
           vJrwpWtwJgWrhcsFMMfFFhFp
           jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
           PmmdzqPrVvPwwTWBwg
           wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
           ttgJtRGJQctTZtZT
           CrZsJsPPZsGzwwsLwLmpwMDw
       """.trimIndent()
        val parsed: List<Rucksack> = parse(inputSmall)
        parsed shouldBe listOf(
            "vJrwpWtwJgWr" to "hcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGL" to "rsFMfFZSrLrFZsSL",
            "PmmdzqPrV" to "vPwwTWBwg",
            "wMqvLMZHhHMvwLH" to "jbvcjnnSBnvTQFn",
            "ttgJtRGJ" to "QctTZtZT",
            "CrZsJsPPZsGz" to "wwsLwLmpwMDw",
        )
        val result = process(parsed)
        result shouldBe 1
    }

    }


val input = """
    <here be input>
""".trimIndent()
