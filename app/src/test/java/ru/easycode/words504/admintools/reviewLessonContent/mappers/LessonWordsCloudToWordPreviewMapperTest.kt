package ru.easycode.words504.admintools.reviewLessonContent.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.WordsPreview

class LessonWordsCloudToWordPreviewMapperTest {

    @Test
    fun `test map`() {
        val cloudList = listOf(
            LessonWordCloud.Base(
                id = "first",
                definitions = listOf(
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base(
                                ui = "first",
                                index = 0,
                                dictionaryForm = "first"
                            )
                        )
                    )
                ),
                examples = emptyList()
            ),
            LessonWordCloud.Base(
                id = "second",
                definitions = listOf(
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base(
                                ui = "second",
                                index = 0,
                                dictionaryForm = "second"
                            )
                        )
                    )
                ),
                examples = emptyList()
            ),
            LessonWordCloud.Base(
                id = "third",
                definitions = listOf(
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base(
                                ui = "third",
                                index = 0,
                                dictionaryForm = "third"
                            )
                        )
                    )
                ),
                examples = emptyList()
            )
        )
        val expected = WordsPreview.Base(listOf("first", "second", "third"))
        val actual = LessonWordsCloudToWordPreviewMapper.Base(LessonWordCloudToUi()).map(cloudList)
        assertEquals(expected, actual)
    }
}
