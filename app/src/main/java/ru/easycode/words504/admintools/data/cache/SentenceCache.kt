package ru.easycode.words504.admintools.data.cache

import ru.easycode.words504.admintools.presentation.SentenceUi
import ru.easycode.words504.data.Empty

interface SentenceCache : Empty {
    interface Read : ru.easycode.words504.data.Read<SentenceUi>
    interface Save : ru.easycode.words504.data.Save<SentenceUi>
    interface Mutable : Read, Save

    class Base : SentenceCache, Mutable {

        private var data: SentenceUi = SentenceUi.Base("", emptyList())

        override fun read() = data

        override fun save(data: SentenceUi) {
            this.data = data
        }

        override fun isEmpty(): Boolean = data.isEmpty()
    }
}
