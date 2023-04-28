package ru.easycode.words504.languages.presentation

import android.widget.TextView
import java.io.Serializable
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository

interface LanguageUi : Serializable {

    fun click(chooseLanguage: ChooseLanguage)

    fun id(): String

    fun map(textView: TextView)

    abstract class Abstract(
        protected open val id: String,
        protected open val value: String
    ) : LanguageUi {
        override fun id() = id

        override fun map(textView: TextView) {
            textView.text = value
        }
    }

    data class NotChosen(
        override val id: String,
        override val value: String
    ) : Abstract(id, value){
        override fun click(chooseLanguage: ChooseLanguage) {
            chooseLanguage.choose(id)
        }
    }

    data class Chosen(
        override val id: String,
        override val value: String
    ) : Abstract(id, value){
        override fun click(chooseLanguage: ChooseLanguage) = Unit
    }
}

class LanguageUiMapper : LanguageCache.Mapper<LanguageUi> {
    override fun map(key: String, name: String) = LanguageUi.NotChosen(key, name)
}

class LanguageUiChosenMapper(
    private val repository: ChooseLanguageRepository,
    private val mapper: LanguageCache.Mapper<LanguageUi>
) : LanguageCache.Mapper<List<LanguageUi>> {

    override fun map(key: String, name: String): List<LanguageUi> {
        val languages = repository.languages()
        return languages.map {
            if (it.same(key)) {
                LanguageUi.Chosen(key, name)
            } else {
                it.map(mapper)
            }
        }
    }
}
