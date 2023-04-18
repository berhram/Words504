package ru.easycode.words504.admintools.presentation

interface SentenceUiCache {
    interface Read : ru.easycode.words504.data.Read<SentenceUi>
    interface Save : ru.easycode.words504.data.Save<SentenceUi>
    interface Mutable : Read, Save

    class Base : SentenceUiCache, Mutable {

        private var data: SentenceUi = SentenceUi.Base("", emptyList())

        override fun read() = data

        override fun save(data: SentenceUi) {
            this.data = data
        }
    }
}
