package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface ExerciseItemCloud {

    interface Mapper<T : Any> {
        fun map(
            question: SentenceCloud,
            correctAnswerId: List<String>,
            answers: List<SentenceCloud>
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("question")
        private val question: SentenceCloud,
        @SerializedName("question")
        private val correctAnswerId: List<String>,
        @SerializedName("answers")
        private val answers: List<SentenceCloud>
    ) : ExerciseItemCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(question, correctAnswerId, answers)
    }
}
