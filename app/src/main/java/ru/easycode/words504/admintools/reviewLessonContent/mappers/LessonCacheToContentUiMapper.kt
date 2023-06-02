package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentUi

class LessonCacheToContentUiMapper(
    private val mapper: LessonCloud.Mapper<List<ReviewLessonContentUi>>
) : LessonCache.Mapper<List<ReviewLessonContentUi>> {
    override fun map(
        id: String,
        isComplete: Boolean,
        lessonCloud: LessonCloud
    ): List<ReviewLessonContentUi> = lessonCloud.map(mapper)
}
