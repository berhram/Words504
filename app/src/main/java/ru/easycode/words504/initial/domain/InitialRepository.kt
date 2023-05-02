package ru.easycode.words504.initial.domain

interface InitialRepository {

    fun userHasChosenLanguage(): Boolean

    suspend fun init()
}
