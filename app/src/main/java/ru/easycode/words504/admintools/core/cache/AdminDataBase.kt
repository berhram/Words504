package ru.easycode.words504.admintools.core.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonsDao

interface AdminDataBase {
    fun lessonsDao(): LessonsDao

    @Database(
        entities = [LessonCache.Base::class],
        version = 1
    )
    abstract class Base : RoomDatabase(), AdminDataBase
}
