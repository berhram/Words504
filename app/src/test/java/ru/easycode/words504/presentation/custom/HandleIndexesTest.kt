package ru.easycode.words504.presentation.custom

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.domain.Mapper

class HandleIndexesTest {
    private lateinit var handler: Mapper<String, String>

    @Before
    fun setup() {
        handler = HandleIndexes(LetterMatch(), DelimiterMatch())
    }

    @Test
    fun test_simple_index_input() {
        val input = "Strike while the iron is hot"
        val expected = "0______7_____13___17____22__25__"
        val actual = handler.map(input)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_removing() {
        val firstInput = "It's darkest before the dawn"
        val expectedInitial = "0____5_______13______20___24___"
        val actualInitial = handler.map(firstInput)
        assertEquals(expectedInitial, actualInitial)

        val removeOneLetter = "It's darkest before the daw"
        val expectedOneLetterRemoved = "0____5_______13______20___24__"
        val actualOneLetterRemoved = handler.map(removeOneLetter)
        assertEquals(expectedOneLetterRemoved, actualOneLetterRemoved)

        val removeOneWord = "It's darkest before the"
        val expectedOneWordRemoved = "0____5_______13______20__"
        val actualOneWordRemoved = handler.map(removeOneWord)
        assertEquals(expectedOneWordRemoved, actualOneWordRemoved)

        val removeAll = ""
        val expectedRemoveAll = ""
        val actualRemoveAll = handler.map(removeAll)
        assertEquals(expectedRemoveAll, actualRemoveAll)

        val inputPart = "It's darkest be"
        val expectedPart = "0____5_______13_"
        val actualPart = handler.map(inputPart)
        assertEquals(expectedPart, actualPart)

        val inputWithSpaceEnding = "It's darkest before the dawn "
        val expectedWithSpaceEnding = "0____5_______13______20___24____"
        val actualWithSpaceEnding = handler.map(inputWithSpaceEnding)
        assertEquals(expectedWithSpaceEnding, actualWithSpaceEnding)
    }

    @Test
    fun test_indexes_input_with_punctuation_marks() {
        val sentence =
            "When can their glory fade? O the wild charge they made! All the world wonder'd."
        val expected =
            "0____5___9_____15_____21_____27_" +
                "29___33____38______45____50_____56___60___64_____70________"
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_punctuation_in_between_spaces() {
        val sentence = "Some of them - are not very honest men"
        val expected = "0____5__8______15___19___23____28______35__"
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_multiple_spaces() {
        val sentence = "Some of them   are not very honest men"
        val expected = "0____5__8______15___19___23____28______35__"
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }
}
