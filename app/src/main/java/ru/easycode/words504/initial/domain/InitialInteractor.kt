package ru.easycode.words504.initial.domain

import ru.easycode.words504.domain.HandleError

interface InitialInteractor {

    suspend fun init(): InitialResult

    class Base(
        private val repository: InitialRepository,
        private val handleError: HandleError<java.lang.Exception, String>
    ) : InitialInteractor {

        override suspend fun init(): InitialResult = try {
            if (repository.userHasChosenLanguage()) {
                InitialResult.NotFirstOpening
            } else {
                repository.init()
                InitialResult.FirstOpening
            }
        } catch (e: Exception) {
            val message = handleError.handle(e)
            InitialResult.Error(message)
        }
    }
}

interface InitialRepository {

    fun userHasChosenLanguage(): Boolean

    suspend fun init()
}