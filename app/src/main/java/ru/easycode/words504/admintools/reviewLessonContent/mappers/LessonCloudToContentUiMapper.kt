package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentUi
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.ExercisePreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.QuotePreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.TextPreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.WordsPreview
import ru.easycode.words504.presentation.ManageResources

class LessonCloudToContentUiMapper(
    private val quoteMapper: LessonQuoteCloud.Mapper<QuotePreview.Base>,
    private val wordsMapper: LessonWordsCloudToWordPreviewMapper<WordsPreview>,
    private val textMapper: LessonTextCloud.Mapper<TextPreview.Base>,
    private val exerciseMapper: LessonExerciseCloud.Mapper<ExercisePreview.Base>,
    private val manageResources: ManageResources
) :
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
                ReviewLessonContentUi.Quote(manageResources, quote.map(quoteMapper))
            )
        }
        if (text.isEmpty()) {
            return contentList
        } else {
            contentList.add(
                ReviewLessonContentUi.Text(manageResources, text.map(textMapper))
            )
        }
        if (words.isEmpty()) {
            return contentList
        } else {
            contentList.add(
                ReviewLessonContentUi.Words(manageResources, wordsMapper.map(words))
            )
        }
        exercises.forEach { exercise ->
            if (!exercise.isEmpty()) {
                contentList.add(
                    ReviewLessonContentUi.Exercise(
                        manageResources,
                        exercise.map(exerciseMapper)
                    )
                )
            }
        }
        return contentList
    }
}
