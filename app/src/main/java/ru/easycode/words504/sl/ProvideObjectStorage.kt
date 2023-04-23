package ru.easycode.words504.sl

import ru.easycode.words504.data.cache.storage.ObjectStorage

interface ProvideObjectStorage {
    fun provideObjectStorage(): ObjectStorage
}
