package ru.easycode.words504.languages.presentation

interface ChooseLanguageState {
    abstract class Abstract(protected open val languages: List<LanguageUi>) : ChooseLanguageState
    data class Initial(override val languages: List<LanguageUi>) : Abstract(languages)
    data class Chosen(override val languages: List<LanguageUi>) : Abstract(languages)
}
