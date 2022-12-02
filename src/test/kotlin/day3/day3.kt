package day3

import day3.Parser.parse
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

typealias ChangeMe = Int

object Parser {
    fun parse(input: String): ChangeMe {
        TODO("Not yet implemented")
    }
}

fun process(parsed: ChangeMe): ChangeMe {
    TODO("Not yet implemented")
}

class Day3Tests {
    @Test
    internal fun `test first`() {
       val inputSmall = ""
       val parsed : ChangeMe= parse(inputSmall)
        val result = process(parsed)
        result shouldBe 1
    }

    }


val input = """
    <here be input>
""".trimIndent()
