package ru.easycode.words504.admintools.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore
import ru.easycode.words504.presentation.Screen

interface SentenceViewModel {

    fun init(saveAndRestore: SaveAndRestore<SentenceUi>)
    fun save(saveAndRestore: SaveAndRestore<SentenceUi>, sentence: SentenceUi)
    fun save(sentenceUi: SentenceUi)
    fun goBack()

    class Base(
        private val sentenceUiCache: SentenceUiCache.Mutable,
        private val navigation: NavigationCommunication.Update,
        private val communication: Communication.Mutable<SentenceUi>
    ) : ViewModel(), SentenceViewModel {

        override fun init(saveAndRestore: SaveAndRestore<SentenceUi>) {
            communication.map(
                if (saveAndRestore.isEmpty()) {
                    sentenceUiCache.read()
                } else {
                    saveAndRestore.restore()
                }
            )
        }

        override fun save(saveAndRestore: SaveAndRestore<SentenceUi>, sentence: SentenceUi) {
            saveAndRestore.save(sentence)
        }

        override fun save(sentenceUi: SentenceUi) {
            sentenceUiCache.save(sentenceUi)
        }

        override fun goBack() {
            navigation.map(Screen.Pop)
        }
    }
}
