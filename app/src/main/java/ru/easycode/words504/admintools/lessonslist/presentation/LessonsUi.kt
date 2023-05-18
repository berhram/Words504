package ru.easycode.words504.admintools.lessonslist.presentation

interface LessonsUi {
    data class Success(private val list: List<LessonUi>) : LessonsUi
}
