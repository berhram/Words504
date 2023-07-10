package ru.easycode.words504.languages.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainScreen
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore

interface ChooseLanguageViewModel : ChooseLanguage {

    fun init(saveAndRestore: SaveAndRestore<LanguageCache>)
    fun save(saveAndRestore: SaveAndRestore<LanguageCache>)
    fun save()

    class Base(
        private val communication: Communication.Mutable<ChooseLanguageState>,
        private val repository: ChooseLanguageRepository,
        private val navigation: NavigationCommunication.Update,
        private val mapper: LanguageCache.Mapper<List<LanguageUi>>
    ) : ViewModel(), ChooseLanguageViewModel, Communication.Observe<ChooseLanguageState> {

        override fun observe(owner: LifecycleOwner, observer: Observer<ChooseLanguageState>) =
            communication.observe(owner, observer)

        override fun init(saveAndRestore: SaveAndRestore<LanguageCache>) {
            val userChoice = if (saveAndRestore.isEmpty()) {
                repository.userChoice()
            } else {
                saveAndRestore.restore().also { repository.saveUserChoice(it) }
            }
            communication.map(
                if (userChoice.isEmpty()) {
                    ChooseLanguageState.Initial(userChoice.map(mapper))
                } else {
                    ChooseLanguageState.Chosen(userChoice.map(mapper))
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

interface ChooseLanguage {
    fun choose(id: String)
}
