package ru.easycode.words504.admintools.lessonslist.data.cache

import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.admintools.lessonslist.presentation.LessonUi

class LessonCacheToUiMapper : LessonCache.Mapper<LessonUi> {
    override fun map(id: String, isComplete: Boolean, lessonCloud: LessonCloud): LessonUi =
        if (isComplete) LessonUi.Complete(id) else LessonUi.InProgress(id)
}
