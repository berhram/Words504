package ru.easycode.words504.admintools.data

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.ExerciseItemCloud
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.data.cloud.WordToLearnCloud
import ru.easycode.words504.data.cache.serialization.Serialization

class LessonCloudModelTest {

    @Test
    fun `test lesson cloud`() {
        val jsonString = javaClass.classLoader?.getResource(PATH_FILE)?.readText() ?: ""

        val serialization = Serialization.Base()
        val lessonCloud = serialization.fromJson(jsonString, LessonCloud.Base::class.java)

        val expected = LessonCloud.Base(
            expectedQuoteCloud(),
            expectedWordsToLearnCloud(),
            expectedTextCloud(),
            expectedExercises()
        )
        assertEquals(expected, lessonCloud)
    }

    private fun expectedExercises() = listOf(
        expectedFillBlankExercise(),
        expectedOppositeExercise(),
        expectedTrueFalseExercise()
    )

    private fun expectedTrueFalseExercise() = LessonExerciseCloud.Base(
        type = ExerciseType.TRUE_FALSE,
        items = listOf(
            ExerciseItemCloud.Base(
                question = SentenceCloud.Base(
                    ui = "Some question",
                    words = listOf(
                        WordCloud.Base("Some", 0, "some"),
                        WordCloud.Base("question", 5, "question")
                    )
                ),
                correctAnswerId = listOf("true"),
                answers = emptyList()
            )
        )
    )

    private fun expectedOppositeExercise() = LessonExerciseCloud.Base(
        type = ExerciseType.OPPOSITES,
        items = listOf(
            ExerciseItemCloud.Base(
                question = SentenceCloud.Base(
                    ui = "first question",
                    words = listOf(
                        WordCloud.Base("first", 0, "first"),
                        WordCloud.Base("question", 6, "question")
                    )
                ),
                correctAnswerId = listOf("answerFirst"),
                answers = listOf(
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base("answerFirst", 0, "answerFirst")
                        )
                    ),
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base("answerSecond", 0, "answerSecond")
                        )
                    )
                )
            ),
            ExerciseItemCloud.Base(
                question = SentenceCloud.Base(
                    ui = "second question",
                    words = listOf(
                        WordCloud.Base("second", 0, "second"),
                        WordCloud.Base("question", 7, "question")
                    )
                ),
                correctAnswerId = listOf("answerSecond"),
                answers = listOf()
            )
        )
    )

    private fun expectedFillBlankExercise() = LessonExerciseCloud.Base(
        type = ExerciseType.FILL_BLANK,
        items = listOf(
            ExerciseItemCloud.Base(
                question = SentenceCloud.Base(
                    ui = "Fill blank word",
                    words = listOf(
                        WordCloud.Base("Fill", 0, "fill"),
                        WordCloud.Base("blank", 6, "blank"),
                        WordCloud.Base("word", 12, "word")
                    )
                ),
                correctAnswerId = listOf("blank"),
                answers = listOf(
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base("wrong", 0, "wrong")
                        )
                    ),
                    SentenceCloud.Base(
                        ui = "",
                        words = listOf(
                            WordCloud.Base("blank", 0, "blank")
                        )
                    )
                )
            )

        )
    )

    private fun expectedTextCloud() = LessonTextCloud.Base(
        title = SentenceCloud.Base(
            ui = "Title text",
            words = listOf(
                WordCloud.Base("Title", 0, "title"),
                WordCloud.Base("text", 7, "text")
            )
        ),
        sentences = listOf(
            SentenceCloud.Base(
                ui = "Some sentence one",
                words = listOf(
                    WordCloud.Base("Some", 0, "some"),
                    WordCloud.Base("sentence", 5, "sentence"),
                    WordCloud.Base("one", 14, "one")
                )
            ),
            SentenceCloud.Base(
                ui = "Some sentence two",
                words = listOf(
                    WordCloud.Base("Some", 0, "some"),
                    WordCloud.Base("sentence", 5, "sentence"),
                    WordCloud.Base("two", 14, "two")
                )
            )
        )
    )

    private fun expectedWordsToLearnCloud() = listOf(
        WordToLearnCloud.Base(
            id = "firstWord",
            definitions = listOf(
                SentenceCloud.Base(
                    ui = "firstWord is something",
                    words = listOf(
                        WordCloud.Base("firstWord", 0, "firstWord"),
                        WordCloud.Base("something", 13, "something")
                    )
                ),
                SentenceCloud.Base(
                    ui = "firstWord is something",
                    words = listOf(
                        WordCloud.Base("firstWord", 0, "firstWord"),
                        WordCloud.Base("something", 13, "something")
                    )
                )
            ),
            examples = listOf(
                SentenceCloud.Base(
                    ui = "firstWord example one",
                    words = listOf(
                        WordCloud.Base("firstWord", 0, "firstWord"),
                        WordCloud.Base("example", 10, "example"),
                        WordCloud.Base("one", 18, "one")
                    )
                ),
                SentenceCloud.Base(
                    ui = "firstWord example two",
                    words = listOf(
                        WordCloud.Base("firstWord", 0, "firstWord"),
                        WordCloud.Base("example", 10, "example"),
                        WordCloud.Base("two", 18, "two")
                    )
                )
            )
        ),
        WordToLearnCloud.Base(
            id = "secondWord",
            definitions = listOf(
                SentenceCloud.Base(
                    ui = "secondWord is something",
                    words = listOf(
                        WordCloud.Base("secondWord", 0, "secondWord"),
                        WordCloud.Base("something", 13, "something")
                    )
                )
            ),
            examples = listOf(
                SentenceCloud.Base(
                    ui = "secondWord example one",
                    words = listOf(
                        WordCloud.Base("secondWord", 0, "secondWord"),
                        WordCloud.Base("example", 10, "example"),
                        WordCloud.Base("one", 18, "one")
                    )
                ),
                SentenceCloud.Base(
                    ui = "secondWord example two",
                    words = listOf(
                        WordCloud.Base("secondWord", 0, "secondWord"),
                        WordCloud.Base("example", 10, "example"),
                        WordCloud.Base("two", 18, "two")
                    )
                )
            )
        )
    )

    private fun expectedQuoteCloud(): LessonQuoteCloud.Base = LessonQuoteCloud.Base(
        value = listOf(
            SentenceCloud.Base(
                ui = "Now or never",
                words = listOf(
                    WordCloud.Base("Now", 0, "now"),
                    WordCloud.Base("or", 4, "or"),
                    WordCloud.Base("never", 7, "never")
                )
            )
        ),
        author = "Someone"
    )

    companion object {
        private const val PATH_FILE = "raw/lesson.json"
    }
}
