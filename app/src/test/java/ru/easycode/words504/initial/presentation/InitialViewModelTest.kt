package ru.easycode.words504.initial.presentation

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.MainScreen
import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.initial.domain.InitialResult
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen

class InitialViewModelTest : BaseTest() {

    private lateinit var interactor: FakeInitialInteractor
    private lateinit var communication: FakeInitialCommunication
    private lateinit var viewModel: InitialViewModel

    @Before
    fun setup() {
        interactor = FakeInitialInteractor.Base(functionsCallsStack)
        communication = FakeInitialCommunication.Base(functionsCallsStack)
        viewModel = InitialViewModel(
            interactor = interactor,
            communication = communication,
            navigation = fakeNavigation,
            dispatchers = TestDispatchersList()
        )
    }

    @Test
    fun `first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.FirstOpening)
        viewModel.init()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.FirstOpening)
        fakeNavigation.same(ChooseLanguageScreen)
        functionsCallsStack.checkStack(3)
    }

    @Test
    fun `not first opening success`() = runBlocking {
        interactor.changeExpected(InitialResult.NotFirstOpening)
        viewModel.init()
        communication.same(InitialUiState.Loading)
        interactor.same(InitialResult.NotFirstOpening)
        fakeNavigation.same(MainScreen)
        functionsCallsStack.checkStack(3)
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
        fakeNavigation.same(ChooseLanguageScreen)
        functionsCallsStack.checkStack(6)
    }

    private interface FakeInitialInteractor : InitialInteractor {

        fun same(other: InitialResult)

        fun changeExpected(expected: InitialResult)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeInitialInteractor {
            private lateinit var result: InitialResult

            override fun same(other: InitialResult) {
                assertEquals(result, other)
                functionCallsStack.checkCalled(INTERACTOR_CALLED)
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

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeInitialCommunication {
            private val list = mutableListOf<InitialUiState>()
            private var index = 0

            override fun map(source: InitialUiState) {
                functionCallsStack.put(COMMUNICATION_CALLED)
                list.add(source)
            }

            override fun same(other: InitialUiState) {
                assertEquals(list[index++], other)
                functionCallsStack.checkCalled(COMMUNICATION_CALLED)
            }
        }

        companion object {
            private const val COMMUNICATION_CALLED = "communication#map"
        }
    }
}
