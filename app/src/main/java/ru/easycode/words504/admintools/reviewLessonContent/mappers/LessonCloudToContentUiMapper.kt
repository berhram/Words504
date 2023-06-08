package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
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
        if (!quote.isEmpty()) {
            contentList.add(ReviewLessonContentUi.Quote(manageResources, quote))
        } else {
            return contentList
        }
        if (!text.isEmpty()) {
            contentList.add(ReviewLessonContentUi.Text(manageResources, text))
        } else {
            return contentList
        }
        if (words.isNotEmpty()) {
            contentList.add(ReviewLessonContentUi.Words(manageResources, words))
        } else {
            return contentList
        }
        exercises.forEach { exercise ->
            if (!exercise.isEmpty()) {
                contentList.add(ReviewLessonContentUi.Exercise(manageResources, exercise))
            }
        }
        return contentList
    }
}
