package ru.easycode.words504.admintools.lessonslist.presentation

import ru.easycode.words504.presentation.ClickListener

interface LessonUiClickListener : ClickListener<LessonUi> {
    fun share(item: LessonUi)
}
