package ru.easycode.words504.sl

import ru.easycode.words504.data.cache.storage.SimpleStorage

interface ProvideSimpleStorage {
    fun provideSimpleStorage(): SimpleStorage
}
