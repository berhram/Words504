package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.domain.LanguageDomain

class FakeDomainMapper : LanguageCache.Mapper<LanguageDomain> {

    override fun map(key: String, name: String) = LanguageDomain(key, name)
}
