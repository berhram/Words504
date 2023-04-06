package ru.easycode.words504.languages.data.cache

interface LanguageKeyMapper : LanguageCache.Mapper<Boolean> {

    class SameLanguage(private val otherKey: String) : LanguageKeyMapper {
        override fun map(key: String, name: String) = otherKey == key
    }
}
