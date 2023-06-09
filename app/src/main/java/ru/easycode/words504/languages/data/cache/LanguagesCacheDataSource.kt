package ru.easycode.words504.languages.data.cache

import com.google.gson.reflect.TypeToken
import ru.easycode.words504.data.cache.storage.ObjectStorage

interface LanguagesCacheDataSource {

    interface Read : ru.easycode.words504.data.Read<List<LanguageCache>>
    interface Save : ru.easycode.words504.data.Save<List<LanguageCache>>
    interface Mutable : Read, Save

    class Base(
        private val objectStorage: ObjectStorage,
        private val languagesKey: String = "LanguagesKey"
    ) : LanguagesCacheDataSource, Mutable {

        override fun read(): List<LanguageCache> = objectStorage.readList(
            languagesKey,
            object : TypeToken<List<LanguageCache.Base>>() {}
        )

        override fun save(data: List<LanguageCache>) = objectStorage.save(languagesKey, data)
    }
}
