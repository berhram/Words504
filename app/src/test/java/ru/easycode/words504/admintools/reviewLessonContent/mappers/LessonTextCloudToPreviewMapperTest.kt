package ru.easycode.words504.admintools.reviewLessonContent.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.TextPreview

class LessonTextCloudToPreviewMapperTest {

    @Test
    fun `test map`() {
        val cloudText = LessonTextCloud.Base(
            title = SentenceCloud.Base(
                ui = "Text title",
                words = listOf(
                    WordCloud.Base(ui = "Text", index = 0, dictionaryForm = "text"),
                    WordCloud.Base(ui = "title", index = 6, dictionaryForm = "title"),
                )
            ),
            sentences = listOf(
                SentenceCloud.Base(
                    ui = "First sentence.",
                    words = listOf(
                        WordCloud.Base(
                            ui = "First",
                            index = 0,
                            dictionaryForm = "First"
                        ),
                        WordCloud.Base(
                            ui = "sentence",
                            index = 6,
                            dictionaryForm = "sentence"
                        )
                    )
                ),
                SentenceCloud.Base(
                    ui = "Second sentence.",
                    words = listOf(
                        WordCloud.Base(
                            ui = "Second",
                            index = 0,
                            dictionaryForm = "second"
                        ),
                        WordCloud.Base(
                            ui = "sentence",
                            index = 7,
                            dictionaryForm = "sentence"
                        )
                    )
                ),
            )
        )

        val mapper = LessonTextCloudToPreviewMapper(SentenceCloud.Mapper.Ui(WordCloud.Mapper.Ui))
        val expected = TextPreview.Base(
            title = "Text title",
            text = "First sentence. Second sentence."
        )
        val actual = cloudText.map(mapper)
        assertEquals(expected, actual)
    }
}
