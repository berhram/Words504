package ru.easycode.words504.admintools.lessonslist.domain

import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache

interface LessonsListRepository {
    suspend fun save(lessonCache: LessonCache.Base)
    suspend fun lesson(id: String): LessonCache.Base
    suspend fun lessons(): List<LessonCache>
    suspend fun lessonToString(id: String): String
    suspend fun chosenLesson(): LessonCache.Base
    fun chooseLesson(id: String)
}
