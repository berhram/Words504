package ru.easycode.words504.admintools.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore
import ru.easycode.words504.presentation.Screen

interface SentenceViewModel {

    fun init(saveAndRestore: SaveAndRestore<SentenceUi>)
    fun sentence(data: SentenceUi)
    fun save()
    fun backPressed()

    class Base(
        private val sentenceUiCache: SentenceUiCache.Mutable,
        private val navigation: NavigationCommunication.Update,
        private val communication: Communication.Mutable<SentenceUi>
    ) : ViewModel(), SentenceViewModel {

        private var sentenceUi: SentenceUi = SentenceUi.Base("", emptyList())

        override fun init(saveAndRestore: SaveAndRestore<SentenceUi>) {
            sentence(saveAndRestore.restore())
        }

        override fun sentence(data: SentenceUi) {
            sentenceUi = data
            communication.map(sentenceUi)
        }

        override fun save() {
            sentenceUiCache.save(sentenceUi)
        }

        override fun backPressed() {
            navigation.map(Screen.Pop)
        }
    }
}
