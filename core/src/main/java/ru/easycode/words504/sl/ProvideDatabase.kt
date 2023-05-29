package ru.easycode.words504.sl

interface ProvideDatabase<T : Any> {
    fun provideDatabase(): T
}
