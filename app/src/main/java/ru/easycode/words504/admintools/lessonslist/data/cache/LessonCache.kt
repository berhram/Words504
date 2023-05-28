package ru.easycode.words504.admintools.lessonslist.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

interface LessonCache {

    interface Mapper<T> {
        fun map(id: String, isComplete: Boolean): T
    }

    fun <T> map(mapper: Mapper<T>): T

    @Entity(tableName = "admin_lessons_table")
    data class Base(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "isComplete")
        val isComplete: Boolean,
        @ColumnInfo(name = "json")
        val jsonData: String
    ) : LessonCache {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, isComplete)
    }
}
