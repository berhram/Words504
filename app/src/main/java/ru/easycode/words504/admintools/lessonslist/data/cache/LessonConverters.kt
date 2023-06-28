package ru.easycode.words504.admintools.lessonslist.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.data.cache.GenericConverter
import ru.easycode.words504.data.cache.serialization.Serialization

@ProvidedTypeConverter
class LessonConverters(private val serialization: Serialization) :
    GenericConverter<LessonCloud.Base, String> {

    @TypeConverter
    override fun fromObject(obj: LessonCloud.Base): String = serialization.toJson(obj)

    @TypeConverter
    override fun toObject(value: String): LessonCloud.Base =
        serialization.fromJson(value, TypeToken.get(LessonCloud.Base::class.java))
}
