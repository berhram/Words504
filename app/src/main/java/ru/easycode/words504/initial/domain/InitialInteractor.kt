package ru.easycode.words504.initial.domain

interface InitialInteractor {
    suspend fun init(): InitialResult
}