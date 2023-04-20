package ru.easycode.words504.languages.presentation

interface ChooseLanguageState {
    data class Initial(private val languages: List<LanguageUi>) : ChooseLanguageState
    data class Chosen(private val languages: List<LanguageUi>) : ChooseLanguageState
}
