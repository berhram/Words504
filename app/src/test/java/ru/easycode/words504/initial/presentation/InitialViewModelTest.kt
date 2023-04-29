package ru.easycode.words504.initial.presentation

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.MainScreen
import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.initial.domain.InitialResult
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

class InitialViewModelTest : BaseTest() {

    private lateinit var interactor: FakeInitialInteractor
    private lateinit var communication: FakeInitialCommunication
    private lateinit var navigation: FakeNavigation
    private lateinit var viewModel: InitialViewModel

    @Before
    fun setup() {
        interactor = FakeInitialInteractor.Base()
        communication = FakeInitialCommunication.Base()
        navigation = FakeNavigation.Base()
        viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation,
            dispatchers = TestDispatchersList()
        )
    }

    @Test
    fun `first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.FirstOpening())
        viewModel.init()
        communication.same(InitialUiState.Loading, 0)
        navigation.same(ChooseLanguageScreen, 0)
    }

    @Test
    fun `not first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.NotFirstOpening())
        viewModel.init()
        communication.same(InitialUiState.Loading, 0)
        navigation.same(MainScreen, 0)
    }

    @Test
    fun `first opening failure then retry and success`() = runBlocking {
        interactor.changeExpected(InitialResult.Error(message = "no connection"))
        viewModel.init()
        communication.same(InitialUiState.Loading, 0)
        communication.same(InitialUiState.Error(message = "no connection"), 1)
        interactor.changeExpected(InitialResult.FirstOpening())
        viewModel.retry()
        communication.same(InitialUiState.Loading, 2)
        navigation.same(ChooseLanguageScreen, 0)
    }
}

private interface FakeInitialInteractor : InitialInteractor {

    fun same(other: InitialResult)

    fun changeExpected(expected: InitialResult)

    class Base : FakeInitialInteractor {
        private lateinit var result: InitialResult

        override fun same(other: InitialResult) = assertEquals(result, other)

        override fun changeExpected(expected: InitialResult) {
            result = expected
        }

        override suspend fun init(): InitialResult {
            return result
        }
    }
}

private interface FakeInitialCommunication : InitialCommunication {

    fun same(other: InitialUiState, index: Int)

    class Base : FakeInitialCommunication {
        private val list = mutableListOf<InitialUiState>()

        override fun map(source: InitialUiState) {
            list.add(source)
        }

        override fun same(other: InitialUiState, index: Int) = assertEquals(list[index], other)
    }
}

private interface FakeNavigation : NavigationCommunication.Update {

    fun same(other: Screen, index: Int)

    class Base : FakeNavigation {
        private val list = mutableListOf<Screen>()

        override fun same(other: Screen, index: Int) = assertEquals(list[index], other)

        override fun map(source: Screen) {
            list.add(source)
        }
    }
}
