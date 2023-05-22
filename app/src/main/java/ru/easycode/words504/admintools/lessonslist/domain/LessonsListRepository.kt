package ru.easycode.words504.admintools.lessonslist.domain

import ru.easycode.words504.admintools.lessonslist.data.LessonCache

interface LessonsListRepository {
    fun lessons(): List<LessonCache>
    fun chooseLesson(id: String)
    fun lessonToString(id: String): String
}
