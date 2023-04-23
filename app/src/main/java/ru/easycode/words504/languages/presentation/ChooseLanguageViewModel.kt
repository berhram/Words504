package ru.easycode.words504.languages.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainScreen
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore

interface ChooseLanguageViewModel {

    fun init(saveAndRestore: SaveAndRestore<LanguageCache>)
    fun choose(id: String)
    fun save(saveAndRestore: SaveAndRestore<LanguageCache>)
    fun save()

    class Base(
        private val communication: Communication.Mutable<ChooseLanguageState>,
        private val repository: ChooseLanguageRepository,
        private val navigation: NavigationCommunication.Update,
        private val mapper: LanguageCache.Mapper<List<LanguageUi>>
    ) : ViewModel(), ChooseLanguageViewModel {

        override fun init(saveAndRestore: SaveAndRestore<LanguageCache>) {
            communication.map(
                if (saveAndRestore.isEmpty()) {
                    val userChoice = repository.userChoice()
                    ChooseLanguageState.Initial(userChoice.map(mapper))
                } else {
                    ChooseLanguageState.Chosen(saveAndRestore.restore().map(mapper))
                }
            )
        }

        override fun choose(id: String) {
            val language = repository.languages().find { it.same(id) }!!
            repository.saveUserChoice(language)
            communication.map(ChooseLanguageState.Chosen(language.map(mapper)))
        }

        override fun save(saveAndRestore: SaveAndRestore<LanguageCache>) {
            saveAndRestore.save(repository.userChoice())
        }

        override fun save() {
            repository.save()
            navigation.map(MainScreen)
        }
    }
}
