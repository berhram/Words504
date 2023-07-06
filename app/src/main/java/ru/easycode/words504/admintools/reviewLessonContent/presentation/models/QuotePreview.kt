package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

data class QuotePreview(
    private val quote: String,
    private val author: String
) : Preview {
    override fun content(): String = "$quote \n\t$author"
}
