package ru.easycode.words504.admintools.lessonslist.presentation

interface LessonState {
    object InProgress : LessonState
    object Complete : LessonState
}
