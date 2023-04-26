package ru.easycode.words504.initial.presentation

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.easycode.words504.MainScreen
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

class InitialViewModelTest {

    @Test
    fun `first opening success`() = runBlocking {
        val interactor = FakeInitialInteractor(InitialResult.FirstOpening())
        val communication = FakeInitialCommunication()
        val navigation = FakeNavigation()
        val viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation
        )

        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(ChooseLanguageScreen, navigation.list[0])
    }

    @Test
    fun `not first opening success`() = runBlocking {
        val interactor = FakeInitialInteractor(InitialResult.NotFirstOpening())
        val communication = FakeInitialCommunication()
        val navigation = FakeNavigation()
        val viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation
        )

        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(MainScreen, navigation.list[0])
    }

    @Test
    fun `first opening failure then retry and success`() = runBlocking {
        val interactor = FakeInitialInteractor(InitialResult.Error(message = "no connection"))
        val communication = FakeInitialCommunication()
        val navigation = FakeNavigation()
        val viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation
        )

        viewModel.init()
        assertEquals(InitialUiState.Loading, communication.list[0])
        assertEquals(InitialUiState.Error(message = "no connection"), communication.list[1])
        interactor.result = InitialResult.FirstOpening()
        viewModel.retry()
        assertEquals(InitialUiState.Loading, communication.list[2])
        assertEquals(ChooseLanguageScreen, navigation.list[0])
    }
}

private class FakeInitialInteractor(var result: InitialResult) : InitialInteractor {
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
