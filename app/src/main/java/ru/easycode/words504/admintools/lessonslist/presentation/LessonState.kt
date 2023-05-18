package ru.easycode.words504.admintools.lessonslist.presentation

interface LessonState {
    object InWork : LessonState
    object Complete : LessonState
}
