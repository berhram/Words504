package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

data class WordsPreview(private val words: List<String>) : Preview {
    override fun content(): String = words.joinToString("\n")
}
