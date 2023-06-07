package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.ExerciseItemCloud
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentUi
import ru.easycode.words504.presentation.ManageResources

class LessonExerciseCloudToContentUiMapper(private val manageResources: ManageResources) :
    LessonExerciseCloud.Mapper<ReviewLessonContentUi.Exercise> {

    override fun map(type: ExerciseType, items: List<ExerciseItemCloud>) =
        ReviewLessonContentUi.Exercise(
            manageResources,
            type
        )
}
