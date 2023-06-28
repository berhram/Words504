package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

interface ExercisePreview {
    interface Mapper<T : Any> {
        fun map(type: String, questions: List<String>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(private val type: String, private val questions: List<String>) :
        ExercisePreview {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(type, questions)
    }
}
