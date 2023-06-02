package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.ExerciseItemCloud
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud

class LessonExerciseTypeMapper : LessonExerciseCloud.Mapper<ExerciseType> {
    override fun map(type: ExerciseType, items: List<ExerciseItemCloud>): ExerciseType = type
}
