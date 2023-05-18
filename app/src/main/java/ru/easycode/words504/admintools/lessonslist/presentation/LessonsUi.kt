package ru.easycode.words504.admintools.lessonslist.presentation

interface LessonsUi {

    object Loading : LessonsUi
    object Error : LessonsUi

    data class Success(private val list: List<LessonUi>) : LessonsUi
}
