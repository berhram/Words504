package ru.easycode.words504.admintools.reviewLessonContent.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.ExerciseItemCloud
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.ExercisePreview

class LessonExerciseToLessonPreviewMapperTest {

    @Test
    fun `test map`() {
        val exerciseCloud = LessonExerciseCloud.Base(
            type = ExerciseType.FILL_BLANK,
            items = listOf(
                ExerciseItemCloud.Base(
                    question = SentenceCloud.Base(
                        ui = "First question",
                        words = listOf(
                            WordCloud.Base(ui = "First", index = 0, dictionaryForm = "first"),
                            WordCloud.Base(ui = "question", index = 6, dictionaryForm = "question")
                        )
                    ),
                    correctAnswerId = listOf("first"),
                    answers = listOf(
                        SentenceCloud.Base(
                            ui = "",
                            words = listOf(
                                WordCloud.Base(
                                    ui = "first",
                                    index = 0,
                                    dictionaryForm = "first"
                                )
                            )
                        ),
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
                    )
                ),
                ExerciseItemCloud.Base(
                    question = SentenceCloud.Base(
                        ui = "Second question",
                        words = listOf(
                            WordCloud.Base(ui = "Second", index = 0, dictionaryForm = "second"),
                            WordCloud.Base(ui = "question", index = 7, dictionaryForm = "question")
                        )
                    ),
                    correctAnswerId = emptyList(),
                    answers = emptyList()
                )
            )
        )
        val mapper = LessonExerciseToLessonPreviewMapper(
            ExerciseItemCloudToQuestionString(
                SentenceCloud.Mapper.Ui(WordCloud.Mapper.Ui)
            )
        )
        val expected =
            ExercisePreview.Base("FILL_BLANK", listOf("First question", "Second question"))
        val actual = exerciseCloud.map(mapper)
        assertEquals(expected, actual)
    }
}
