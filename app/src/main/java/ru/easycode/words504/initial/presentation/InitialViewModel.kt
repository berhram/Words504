package ru.easycode.words504.initial.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.Init
import ru.easycode.words504.presentation.NavigationCommunication

class InitialViewModel(
    private val interactor: InitialInteractor,
    private val communication: InitialCommunication,
    private val navigation: NavigationCommunication.Update,
    dispatchers: DispatchersList
) : BaseViewModel(dispatchers), Init, Retry, Communication.Observe<InitialUiState> {

    override fun init() {
        communication.map(InitialUiState.Loading)
        handle({ interactor.init() }) { initialResult ->
            initialResult.map(communication)
            initialResult.map(navigation)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<InitialUiState>) {
        communication.observe(owner, observer)
    }

    override fun retry() {
        init()
    }
}

interface Retry {
    fun retry()
}
