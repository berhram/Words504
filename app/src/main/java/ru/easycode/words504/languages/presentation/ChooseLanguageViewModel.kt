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
        private val mapperChosen: LanguageCache.Mapper<LanguageUi>,
        private val mapperNotChosen: LanguageCache.Mapper<LanguageUi>
    ) : ViewModel(), ChooseLanguageViewModel {

        private val languagesCache = mutableListOf<LanguageCache>()

        override fun init(saveAndRestore: SaveAndRestore<LanguageCache>) {
            languagesCache.addAll(repository.languages())
            if (saveAndRestore.isEmpty()) {
                communication.map(
                    ChooseLanguageState.Initial(
                        languagesCache.map { it.map(mapperNotChosen) }
                    )
                )
            } else {
                val languageRestore = saveAndRestore.restore()
                communication.map(ChooseLanguageState.Chosen(makeChosen(languageRestore)))
            }
        }

        override fun choose(id: String) {
            val language = languagesCache.find { it.same(id) }!!
            repository.userChoice(language)
            communication.map(ChooseLanguageState.Chosen(makeChosen(language)))
        }

        override fun save(saveAndRestore: SaveAndRestore<LanguageCache>) {
            saveAndRestore.save(repository.fetchUserChoice())
        }

        override fun save() {
            repository.save()
            navigation.map(MainScreen)
        }

        private fun makeChosen(language: LanguageCache): List<LanguageUi> =
            languagesCache.map { languageCache ->
                if (languageCache.same(language)) languageCache.map(mapperChosen)
                else languageCache.map(mapperNotChosen)
            }
    }
}
