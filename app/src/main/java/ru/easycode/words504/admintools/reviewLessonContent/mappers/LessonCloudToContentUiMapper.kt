package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.WordToLearnCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentUi
import ru.easycode.words504.presentation.ManageResources

class LessonCloudToContentUiMapper(
    private val manageResources: ManageResources,
    val mapper: LessonExerciseCloud.Mapper<ExerciseType>
) :
    LessonCloud.Mapper<List<ReviewLessonContentUi>> {
    override fun map(
        quote: LessonQuoteCloud,
        words: List<WordToLearnCloud>,
        text: LessonTextCloud,
        exercises: List<LessonExerciseCloud>
    ): List<ReviewLessonContentUi> {
        val contentList = mutableListOf<ReviewLessonContentUi>()
        if (quote.isNotEmpty()) contentList.add(ReviewLessonContentUi.Quote(manageResources))
        if (words.isNotEmpty()) contentList.add(ReviewLessonContentUi.Words(manageResources))
        if (text.isNotEmpty()) contentList.add(ReviewLessonContentUi.Text(manageResources))

        exercises.forEach { exercise ->
            if (exercise.isNotEmpty()) {
                contentList.add(
                    ReviewLessonContentUi.Exercise(
                        manageResources,
                        exercise.map(mapper)
                    )
                )
            }
        }
        return contentList
    }
}
