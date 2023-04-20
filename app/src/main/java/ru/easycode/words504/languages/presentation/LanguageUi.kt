package ru.easycode.words504.languages.presentation

import java.io.Serializable
import ru.easycode.words504.languages.data.cache.LanguageCache

interface LanguageUi : Serializable {
    data class NotChosen(private val id: String, private val value: String) : LanguageUi
    data class Chosen(private val id: String, private val value: String) : LanguageUi
}

class LanguageUiInitialMapper : LanguageCache.Mapper<LanguageUi> {
    override fun map(key: String, name: String) = LanguageUi.NotChosen(key, name)
}

class LanguageUiChosenMapper : LanguageCache.Mapper<LanguageUi> {
    override fun map(key: String, name: String) = LanguageUi.Chosen(key, name)
}
