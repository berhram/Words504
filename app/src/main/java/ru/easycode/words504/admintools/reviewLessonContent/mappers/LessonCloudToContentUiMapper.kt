package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
import ru.easycode.words504.admintools.lessonexercise.presentation.AdminLessonExerciseScreen
import ru.easycode.words504.admintools.lessonquote.presentation.AdminLessonQuoteScreen
import ru.easycode.words504.admintools.lessontext.presentation.AdminLessonTextScreen
import ru.easycode.words504.admintools.lessonwords.presentation.AdminLessonWordsScreen
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentUi
import ru.easycode.words504.presentation.ManageResources

class LessonCloudToContentUiMapper(private val manageResources: ManageResources) :
    LessonCloud.Mapper<List<ReviewLessonContentUi>> {
    override fun map(
        quote: LessonQuoteCloud,
        words: List<LessonWordCloud>,
        text: LessonTextCloud,
        exercises: List<LessonExerciseCloud>
    ): List<ReviewLessonContentUi> {
        val contentList = mutableListOf<ReviewLessonContentUi>()
        if (quote.isEmpty()) {
            return contentList
        } else {
            contentList.add(
                ReviewLessonContentUi.Quote(
                    manageResources,
                    quote,
                    AdminLessonQuoteScreen
                )
            )
        }
        if (text.isEmpty()) {
            return contentList
        } else {
            contentList.add(
                ReviewLessonContentUi.Text(
                    manageResources,
                    text,
                    AdminLessonTextScreen
                )
            )
        }
        if (words.isEmpty()) {
            return contentList
        } else {
            contentList.add(
                ReviewLessonContentUi.Words(
                    manageResources,
                    words,
                    AdminLessonWordsScreen
                )
            )
        }
        exercises.forEach { exercise ->
            if (exercise.isEmpty()) {
                return contentList
            } else {
                contentList.add(
                    ReviewLessonContentUi.Exercise(
                        manageResources,
                        exercise,
                        AdminLessonExerciseScreen
                    )
                )
            }
        }
        return contentList
    }
}
