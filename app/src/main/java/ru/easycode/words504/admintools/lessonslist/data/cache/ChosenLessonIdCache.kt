package ru.easycode.words504.admintools.lessonslist.data.cache

import ru.easycode.words504.data.cache.storage.ObjectStorage

interface ChosenLessonIdCache {
    interface Read : ru.easycode.words504.data.Read<String>
    interface Save : ru.easycode.words504.data.Save<String>
    interface Mutable : Read, Save

    class Base(
        private val objectStorage: ObjectStorage,
        private val lessonKey: String = "ChosenLessonIdKey"
    ) : Mutable {
        override fun read(): String = objectStorage.read(lessonKey, "")

        override fun save(data: String) = objectStorage.save(lessonKey, data)
    }
}
