package ru.easycode.words504.languages.presentation

import android.widget.Button
import ru.easycode.words504.domain.Mapper

interface ChooseLanguageState {
    fun map(adapter: Mapper.Unit<List<LanguageUi>>, saveButton: Button)

    abstract class Abstract(
        private val languages: List<LanguageUi>,
        private val savingEnabled: Boolean
    ) : ChooseLanguageState {

        override fun map(
            adapter: Mapper.Unit<List<LanguageUi>>,
            saveButton: Button
        ) {
            adapter.map(languages)
            saveButton.isEnabled = savingEnabled
        }
    }

    data class Initial(private val languages: List<LanguageUi>) : Abstract(languages, true)

    data class Chosen(private val languages: List<LanguageUi>) : Abstract(languages, true)
}
