package ru.easycode.words504.admintools.lessonslist.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(lessonCache: LessonCache.Base)

    @Query("SELECT * FROM admin_lessons_table WHERE id = :id")
    fun lesson(id: String): LessonCache.Base

    @Query("SELECT * FROM ADMIN_LESSONS_TABLE")
    fun lessons(): List<LessonCache.Base>
}
