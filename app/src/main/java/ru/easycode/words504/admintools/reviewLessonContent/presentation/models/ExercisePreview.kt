package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

data class ExercisePreview(
    private val type: String,
    private val questions: List<String>
) : Preview {
    override fun content(): String = "$type\n${questions.joinToString(separator = "\n")}"
}
