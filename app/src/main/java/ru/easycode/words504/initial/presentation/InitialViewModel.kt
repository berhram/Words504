package ru.easycode.words504.initial.presentation

import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.NavigationCommunication

class InitialViewModel(
    private val interactor: InitialInteractor,
    private val communication: InitialCommunication,
    private val navigation: NavigationCommunication.Update,
    dispatchers: DispatchersList
) : BaseViewModel(dispatchers), InitialInit, InitialRetry {

    override fun init() {
        communication.map(InitialUiState.Loading)
        handle({ interactor.init() }) { initialResult ->
            initialResult.map(communication)
            initialResult.map(navigation)
        }
    }

    override fun retry() {
        init()
    }
}

interface InitialInit {
    fun init()
}

interface InitialRetry {
    fun retry()
}
