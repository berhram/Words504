package ru.easycode.words504.admintools.data.cache

import ru.easycode.words504.admintools.data.SentenceData
import ru.easycode.words504.data.Empty
import ru.easycode.words504.data.cache.storage.ObjectStorage

interface SentenceCache : Empty {
    interface Read : ru.easycode.words504.data.Read<SentenceData>
    interface Save : ru.easycode.words504.data.Save<SentenceData>
    interface Mutable : Read, Save

    class Base(
        private val objectStorage: ObjectStorage,
        private val sentenceKey: String = "SentenceKey"
    ) : SentenceCache, Mutable {

        private val defaultSentence = SentenceData.Base("", emptyList())

        override fun read(): SentenceData {
            return objectStorage.read(sentenceKey, defaultSentence)
        }

        override fun save(data: SentenceData) = objectStorage.save(sentenceKey, data)

        override fun isEmpty(): Boolean = defaultSentence == read()
    }
}
