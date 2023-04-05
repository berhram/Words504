package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cloud.LanguageCloud

class FakeCacheMapper : LanguageCloud.Mapper<LanguageCache> {

    override fun map(languageCode: String, name: String) = LanguageCache.Base(languageCode, name)
}
