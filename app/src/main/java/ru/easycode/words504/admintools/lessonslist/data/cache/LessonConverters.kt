package ru.easycode.words504.admintools.lessonslist.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.data.cache.serialization.Serialization


interface LessonConverters {
    fun toLessonJson(lessonCloud: LessonCloud.Base): String
    fun fromLessonJson(json: String): LessonCloud.Base

    @ProvidedTypeConverter
    class Base(private val serialization: Serialization) : LessonConverters {

        @TypeConverter
        override fun toLessonJson(lessonCloud: LessonCloud.Base): String =
            serialization.toJson(lessonCloud)

        @TypeConverter
        override fun fromLessonJson(json: String): LessonCloud.Base =
            serialization.fromJson(json, TypeToken.get(LessonCloud.Base::class.java))
    }
}
