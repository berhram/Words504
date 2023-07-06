package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

data class TextPreview(private val title: String, private val text: String) : Preview {
    override fun content(): String = "$title \n\n$text "
}
