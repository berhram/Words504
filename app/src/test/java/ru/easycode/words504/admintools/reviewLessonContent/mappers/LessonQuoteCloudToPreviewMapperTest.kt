package ru.easycode.words504.admintools.reviewLessonContent.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.QuotePreview

class LessonQuoteCloudToPreviewMapperTest {
    @Test
    fun `test map`() {
        val cloud = LessonQuoteCloud.Base(
            value = listOf(
                SentenceCloud.Base(
                    "Quote first sentence.",
                    listOf(
                        WordCloud.Base(ui = "Quote", index = 0, dictionaryForm = "quote"),
                        WordCloud.Base(ui = "first", index = 0, dictionaryForm = "first"),
                        WordCloud.Base(ui = "sentence", index = 0, dictionaryForm = "sentence")
                    )
                ),
                SentenceCloud.Base(
                    "Quote second sentence.",
                    listOf(
                        WordCloud.Base(ui = "Quote", index = 0, dictionaryForm = "quote"),
                        WordCloud.Base(ui = "second", index = 0, dictionaryForm = "second"),
                        WordCloud.Base(ui = "sentence", index = 0, dictionaryForm = "sentence")
                    )
                ),
            ),
            author = "Ekaterina"
        )
        val mapper = LessonQuoteCloudToPreviewMapper(SentenceCloud.Mapper.Ui(WordCloud.Mapper.Ui))
        val expected = QuotePreview.Base(
            quote = "Quote first sentence. Quote second sentence.",
            author = "Ekaterina"
        )
        val actual = cloud.map(mapper)
        assertEquals(expected, actual)
    }
}
