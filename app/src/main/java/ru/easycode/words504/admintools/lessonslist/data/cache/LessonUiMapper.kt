package ru.easycode.words504.admintools.lessonslist.data.cache

import ru.easycode.words504.admintools.lessonslist.presentation.LessonUi

class LessonUiMapper : LessonCache.Mapper<LessonUi> {
    override fun map(id: String, isComplete: Boolean): LessonUi =
        if (isComplete) LessonUi.Complete(id) else LessonUi.InProgress(id)
}
