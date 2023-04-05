package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache

class FakeChosenLanguageCache : ChosenLanguageCache.Mutable {

    var data: LanguageCache = LanguageCache.Base("", "")

    override fun read(): LanguageCache = data

    override fun save(data: LanguageCache) {
        this.data = data
    }
}
