package ru.easycode.words504.admintools.lessonslist.presentation

data class LessonUi(
    private val id: String,
    private val status: LessonState,
    private val handleClick: ChooseLesson,
    private val handleShare: ShareLesson
)
