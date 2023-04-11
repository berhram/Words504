package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface LessonExerciseCloud {

    interface Mapper<T : Any> {
        fun map(type: ExerciseType, items: List<ExerciseItemCloud>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("type")
        private val type: ExerciseType,
        @SerializedName("items")
        private val items: List<ExerciseItemCloud.Base>
    ) : LessonExerciseCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(type, items)
    }
}
