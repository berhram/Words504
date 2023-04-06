package ru.easycode.words504.languages.data.cache

class LanguageKeyMapper: LanguageCache.Mapper<String> {
    override fun map(key: String, name: String) = key
}
