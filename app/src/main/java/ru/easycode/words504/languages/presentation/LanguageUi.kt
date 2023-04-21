package ru.easycode.words504.languages.presentation

import java.io.Serializable
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository

interface LanguageUi : Serializable {
    abstract class Abstract(
        protected open val id: String,
        protected open val value: String,
    ) : LanguageUi

    data class NotChosen(
        override val id: String,
        override val value: String,
    ) : Abstract(id, value)

    data class Chosen(
        override val id: String,
        override val value: String,
    ) : Abstract(id, value)
}

class LanguageUiMapper : LanguageCache.Mapper<LanguageUi> {
    override fun map(key: String, name: String) = LanguageUi.NotChosen(key, name)
}

class LanguageUiChosenMapper(
    private val repository: ChooseLanguageRepository,
    private val mapper: LanguageCache.Mapper<LanguageUi>,
) : LanguageCache.Mapper<List<LanguageUi>> {

    override fun map(key: String, name: String) = repository.languages().map {
        if (it.same(key)) {
            LanguageUi.Chosen(key, name)
        } else {
            it.map(mapper)
        }
    }
}
