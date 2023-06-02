package ru.easycode.words504.admintools.lessonslist.data

import ru.easycode.words504.admintools.lessonslist.data.cache.ChosenLessonIdCache
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonsDao
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.data.cache.serialization.Serialization

class LessonsListRepositoryBase(
    private val lessonsDao: LessonsDao,
    private val chosenLessonIdCache: ChosenLessonIdCache.Mutable,
    private val serialization: Serialization
) : LessonsListRepository {

    override suspend fun lesson(id: String): LessonCache.Base = lessonsDao.lesson(id)

    override suspend fun save(lessonCache: LessonCache.Base) = lessonsDao.save(lessonCache)

    override suspend fun lessons(): List<LessonCache> = lessonsDao.lessons()

    override suspend fun lessonToString(id: String): String = serialization.toJson(lesson(id))

    override fun chooseLesson(id: String) = chosenLessonIdCache.save(id)

    override suspend fun chosenLesson(): LessonCache.Base = lesson(chosenLessonIdCache.read())
}
