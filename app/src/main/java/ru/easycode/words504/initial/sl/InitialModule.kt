package ru.easycode.words504.initial.sl

import kotlinx.coroutines.delay
import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.initial.domain.InitialRepository
import ru.easycode.words504.initial.presentation.InitialCommunication
import ru.easycode.words504.initial.presentation.InitialViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class InitialModule(private val core: CoreModule) : Module<InitialViewModel> {

    override fun viewModel(): InitialViewModel {
        val repository = FakeRepository() // todo
        val handleError: HandleError<Exception, String> = FakeHandleError() // todo
        val interactor = InitialInteractor.Base(repository, handleError)
        return InitialViewModel(
            interactor = interactor,
            communication = InitialCommunication.Base(),
            navigation = core.provideNavigation(),
            dispatchers = core.provideDispatchers()
        )
    }

    // todo remove it
    private class FakeRepository : InitialRepository {
        override fun userHasChosenLanguage(): Boolean {
            return false
        }

        override suspend fun init() {
            delay(3000)
            throw Exception()
        }
    }

    // todo remove it
    private class FakeHandleError : HandleError<Exception, String> {
        override fun handle(source: Exception): String = "Error"
    }
}
