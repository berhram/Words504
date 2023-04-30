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
    private lateinit var functionCallsStack: FunctionCallsStack

    @Before
    fun setup() {
        functionCallsStack = FunctionCallsStack.Base()
        interactor = FakeInitialInteractor.Base(functionCallsStack)
        communication = FakeInitialCommunication.Base(functionCallsStack)
        navigation = FakeNavigation.Base(functionCallsStack)
        viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = navigation,
            dispatchers = TestDispatchersList()
        )
    }

    @Test
    fun `first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.FirstOpening)
        viewModel.init()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.FirstOpening)
        navigation.same(ChooseLanguageScreen)
        functionCallsStack.checkFunctionsCalledCount(3)
    }

    @Test
    fun `not first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.NotFirstOpening)
        viewModel.init()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.NotFirstOpening)
        navigation.same(MainScreen)
        functionCallsStack.checkFunctionsCalledCount(3)
    }

    @Test
    fun `first opening failure then retry and success`() = runBlocking {
        interactor.changeExpected(InitialResult.Error(message = "no connection"))
        viewModel.init()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.Error(message = "no connection"))
        communication.same(InitialUiState.Error(message = "no connection"))

        interactor.changeExpected(InitialResult.FirstOpening)
        viewModel.retry()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.FirstOpening)
        navigation.same(ChooseLanguageScreen)
        functionCallsStack.checkFunctionsCalledCount(6)
    }
}

private interface FakeInitialInteractor : InitialInteractor {

    fun same(other: InitialResult)

    fun changeExpected(expected: InitialResult)

    class Base(private val functionCallsStack: FunctionCallsStack) : FakeInitialInteractor {
        private lateinit var result: InitialResult

        override fun same(other: InitialResult) {
            assertEquals(result, other)
            functionCallsStack.check(INTERACTOR_CALLED)
        }

        override fun changeExpected(expected: InitialResult) {
            result = expected
        }

        override suspend fun init(): InitialResult {
            functionCallsStack.put(INTERACTOR_CALLED)
            return result
        }
    }

    companion object {
        private const val INTERACTOR_CALLED = "interactor#init"
    }
}

private interface FakeInitialCommunication : InitialCommunication {

    fun same(other: InitialUiState)

    class Base(private val functionCallsStack: FunctionCallsStack) : FakeInitialCommunication {
        private val list = mutableListOf<InitialUiState>()
        private var index = 0

        override fun map(source: InitialUiState) {
            functionCallsStack.put(COMMUNICATION_CALLED)
            list.add(source)
        }

        override fun same(other: InitialUiState) {
            assertEquals(list[index++], other)
            functionCallsStack.check(COMMUNICATION_CALLED)
        }
    }

    companion object {
        private const val COMMUNICATION_CALLED = "communication#map"
    }
}

private interface FakeNavigation : NavigationCommunication.Update {

    fun same(other: Screen)

    class Base(private val functionCallsStack: FunctionCallsStack) : FakeNavigation {
        private lateinit var screen: Screen

        override fun same(other: Screen) {
            assertEquals(screen, other)
            functionCallsStack.check(NAVIGATION_CALLED)
        }

        override fun map(source: Screen) {
            functionCallsStack.put(NAVIGATION_CALLED)
            screen = source
        }

        companion object {
            private const val NAVIGATION_CALLED = "navigation#map"
        }
    }
}

private interface FunctionCallsStack {

    fun put(funName: String)
    fun check(funName: String)
    fun checkFunctionsCalledCount(count: Int)

    class Base : FunctionCallsStack {
        private val list = mutableListOf<String>()
        private var count = 0

        override fun put(funName: String) {
            list.add(funName)
        }

        override fun check(funName: String) {
            assertEquals(funName, list[count++])
        }

        override fun checkFunctionsCalledCount(count: Int) {
            assertEquals(count, list.size)
        }
    }
}
