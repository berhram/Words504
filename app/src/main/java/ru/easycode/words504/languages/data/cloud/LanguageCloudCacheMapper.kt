package ru.easycode.words504.languages.data.cloud

import ru.easycode.words504.languages.data.cache.LanguageCache

class LanguageCloudCacheMapper : LanguageCloud.Mapper<LanguageCache> {
    override fun map(languageCode: String, name: String) = LanguageCache.Base(languageCode, name)
}
