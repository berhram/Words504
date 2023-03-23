package ru.easycode.words504.data.cache.languages

import ru.easycode.words504.core.LanguageCache
import ru.easycode.words504.data.cache.storage.ObjectStorage

interface LanguagesCacheDataSource {

    interface Read : ru.easycode.words504.core.Read<List<LanguageCache>>
    interface Save : ru.easycode.words504.core.Save<List<LanguageCache>>
    interface Mutable : Read, Save

    class Base(
        private val objectStorage: ObjectStorage,
        private val languagesKey: String = "LanguagesKey",
    ) : LanguagesCacheDataSource, Mutable {

        override fun read(): List<LanguageCache> {
            val defaultLanguages = emptyList<LanguageCache>()
            return objectStorage.read(languagesKey, defaultLanguages)
        }

        override fun save(data: List<LanguageCache>) = objectStorage.save(languagesKey, data)
    }
}
