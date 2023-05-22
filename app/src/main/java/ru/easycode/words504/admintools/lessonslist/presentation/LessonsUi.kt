package ru.easycode.words504.admintools.lessonslist.presentation

interface LessonsUi {
    data class Initial(private val list: List<LessonUi>) : LessonsUi
    data class Share(private val value: String) : LessonsUi
}
