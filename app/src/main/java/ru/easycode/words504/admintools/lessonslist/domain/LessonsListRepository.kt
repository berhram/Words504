package ru.easycode.words504.admintools.lessonslist.domain

import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache

interface LessonsListRepository {
    interface Read {
        suspend fun lesson(id: String): LessonCache.Base
        suspend fun lessons(): List<LessonCache>
        suspend fun lessonToString(id: String): String
        fun chosenLessonId(): String
    }

    interface Save {
        suspend fun save(lessonCache: LessonCache.Base)
        fun saveChooseLesson(id: String)
    }

    interface Mutable : Read, Save
}
