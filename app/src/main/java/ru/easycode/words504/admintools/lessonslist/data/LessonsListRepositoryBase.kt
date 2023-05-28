package ru.easycode.words504.admintools.lessonslist.data

import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonsDao
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.data.cache.storage.SimpleStorage

class LessonsListRepositoryBase(
    private val lessonsDao: LessonsDao,
    private val simpleStorage: SimpleStorage
) : LessonsListRepository {

    override suspend fun lesson(id: String): LessonCache.Base = lessonsDao.lesson(id)

    override suspend fun save(lessonCache: LessonCache.Base) = lessonsDao.save(lessonCache)

    override suspend fun lessons(): List<LessonCache> = lessonsDao.lessons()

    override suspend fun lessonToString(id: String): String = lesson(id).jsonData

    override fun chooseLesson(id: String) = simpleStorage.save(CHOSEN_LESSON_KEY, id)

    companion object {
        private const val CHOSEN_LESSON_KEY = "chosenLesson"
    }
}
