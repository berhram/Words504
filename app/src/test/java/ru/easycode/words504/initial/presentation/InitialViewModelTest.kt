package ru.easycode.words504.initial.presentation

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.MainScreen
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
        interactor = FakeInitialInteractor()
        communication = FakeInitialCommunication()
        navigation = FakeNavigation()
        viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation,
            dispatchers = TestDispatchersList()
        )
    }

    @Test
    fun `first opening success`() = runBlocking {
        interactor.result = InitialResult.FirstOpening()
        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(ChooseLanguageScreen, navigation.list[0])
    }

    @Test
    fun `not first opening success`() = runBlocking {
        interactor.result = InitialResult.NotFirstOpening()
        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(MainScreen, navigation.list[0])
    }

    @Test
    fun `first opening failure then retry and success`() = runBlocking {
        interactor.result = InitialResult.Error(message = "no connection")
        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(InitialUiState.Error(message = "no connection"), communication.list[1])
        interactor.result = InitialResult.FirstOpening()
        viewModel.retry()
        assertEquals(InitialUiState.Loading, communication.list[2])
        assertEquals(ChooseLanguageScreen, navigation.list[0])
    }
}

private class FakeInitialInteractor : InitialInteractor {

    lateinit var result: InitialResult

    override suspend fun init(): InitialResult {
        return result
    }
}

private class FakeInitialCommunication : InitialCommunication {

    val list = mutableListOf<InitialUiState>()

    override fun map(source: InitialUiState) {
        list.add(source)
    }
}

private class FakeNavigation : NavigationCommunication.Update {

    val list = mutableListOf<Screen>()

    override fun map(source: Screen) {
        list.add(source)
    }
}
