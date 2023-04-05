package ru.easycode.words504.presentation.custom

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.admintools.input.ApostropheMatch
import ru.easycode.words504.admintools.input.HandleIndexes
import ru.easycode.words504.admintools.input.LetterMatch
import ru.easycode.words504.domain.Mapper

class HandleIndexesTest {
    private lateinit var handler: Mapper<String, String>

    @Before
    fun setup() {
        handler = HandleIndexes(LetterMatch(), ApostropheMatch())
    }

    @Test
    fun test_simple_index_input() {
        val input = "Strike while the iron is hot"
        val expected = "0..7..13..17..22..25.."
        val actual = handler.map(input)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_removing() {
        val firstInput = "It's darkest before the dawn"
        val expectedInitial = "0..5..13..20..24.."
        val actualInitial = handler.map(firstInput)
        assertEquals(expectedInitial, actualInitial)

        val removeOneLetter = "It's darkest before the daw"
        val expectedOneLetterRemoved = "0..5..13..20..24.."
        val actualOneLetterRemoved = handler.map(removeOneLetter)
        assertEquals(expectedOneLetterRemoved, actualOneLetterRemoved)

        val removeOneWord = "It's darkest before the"
        val expectedOneWordRemoved = "0..5..13..20.."
        val actualOneWordRemoved = handler.map(removeOneWord)
        assertEquals(expectedOneWordRemoved, actualOneWordRemoved)

        val removeAll = ""
        val expectedRemoveAll = ""
        val actualRemoveAll = handler.map(removeAll)
        assertEquals(expectedRemoveAll, actualRemoveAll)

        val inputPart = "It's darkest be"
        val expectedPart = "0..5..13.."
        val actualPart = handler.map(inputPart)
        assertEquals(expectedPart, actualPart)

        val inputWithSpaceEnding = "It's darkest before the dawn "
        val expectedWithSpaceEnding = "0..5..13..20..24.."
        val actualWithSpaceEnding = handler.map(inputWithSpaceEnding)
        assertEquals(expectedWithSpaceEnding, actualWithSpaceEnding)
    }

    @Test
    fun test_indexes_input_with_punctuation_marks() {
        val sentence =
            "When can their glory fade? O the wild charge they made! All the world wonder'd."
        val expected =
            "0..5..9..15..21..27..29..33..38..45..50..56..60..64..70.."
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_punctuation_in_between_spaces() {
        val sentence = "Some of them - are not very honest men"
        val expected = "0..5..8..15..19..23..28..35.."
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_multiple_spaces() {
        val sentence = "Some of them   are not very honest men"
        val expected = "0..5..8..15..19..23..28..35.."
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_quotations() {
        val sentence = "On the box was a label that read, \"Fragile! Handle with care!\""
        val expected = "0..3..7..11..15..17..23..28..35..44..51..56.."
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }

    @Test
    fun test_indexes_input_with_quotations_at_start() {
        val sentence = "\"Fragile!, Handle with care!\" - the label said"
        val expected = "1..11..18..23..32..36..42.."
        val actual = handler.map(sentence)
        assertEquals(expected, actual)
    }
}
