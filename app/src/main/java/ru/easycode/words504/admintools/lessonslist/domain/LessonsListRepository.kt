package ru.easycode.words504.admintools.lessonslist.domain

import ru.easycode.words504.admintools.lessonslist.data.LessonCache

interface LessonsListRepository {
    suspend fun lessons(): List<LessonCache>
}
