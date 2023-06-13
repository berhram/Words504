package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface LessonCloud {

    interface Mapper<T : Any> {
        fun map(
            quote: LessonQuoteCloud,
            words: List<LessonWordCloud>,
            text: LessonTextCloud,
            exercises: List<LessonExerciseCloud>
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("quote")
        private val quote: LessonQuoteCloud.Base,
        @SerializedName("wordsToLearn")
        private val words: List<LessonWordCloud.Base>,
        @SerializedName("text")
        private val text: LessonTextCloud.Base,
        @SerializedName("exercises")
        private val exercises: List<LessonExerciseCloud.Base>
    ) : LessonCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(quote, words, text, exercises)
    }
}
