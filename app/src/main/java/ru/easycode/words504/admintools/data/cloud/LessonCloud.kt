package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface LessonCloud {

    interface Mapper<T : Any> {
        fun map(
            quote: LessonQuoteCloud,
            words: List<WordToLearnCloud>,
            text: LessonTextCloud,
            exercises: List<LessonExerciseCloud>
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("quote")
        private val quote: LessonQuoteCloud,
        @SerializedName("wordsToLearn")
        private val words: List<WordToLearnCloud>,
        @SerializedName("text")
        private val text: LessonTextCloud,
        @SerializedName("exercises")
        private val exercises: List<LessonExerciseCloud>
    ) : LessonCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(quote, words, text, exercises)
    }
}
