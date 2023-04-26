package ru.easycode.words504.initial.presentation

import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.recognition.presentation.Init

class InitialViewModel(dispatchers: DispatchersList) : BaseViewModel(dispatchers), Init {
    override fun init(isFirstRun: Boolean) {
        handle({interactor.init()}){ initialResult->
            initialResult.map(communication, navigation)
        }
    }
}

//todo retry
