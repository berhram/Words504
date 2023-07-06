package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.ExerciseItemCloud
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.ExercisePreview

class LessonExerciseToLessonPreviewMapper(private val mapper: ExerciseItemCloud.Mapper<String>) :
    LessonExerciseCloud.Mapper<ExercisePreview> {
    override fun map(type: ExerciseType, items: List<ExerciseItemCloud>): ExercisePreview {
        val questions = items.map { it.map(mapper) }
        return ExercisePreview(type.name, questions)
    }
}

class ExerciseItemCloudToQuestionString(private val mapper: SentenceCloud.Mapper<String>) :
    ExerciseItemCloud.Mapper<String> {
    override fun map(
        question: SentenceCloud,
        correctAnswerId: List<String>,
        answers: List<SentenceCloud>
    ): String = question.map(mapper)
}
