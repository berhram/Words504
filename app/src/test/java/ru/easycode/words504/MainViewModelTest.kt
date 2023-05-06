package ru.easycode.words504

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.initial.presentation.InitialScreen
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

class MainViewModelTest : BaseTest() {

    private lateinit var fakeCommunication: FakeCommunication
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        fakeCommunication = FakeCommunication.Base(functionsCallsStack)
        viewModel = MainViewModel.Base(fakeCommunication)
    }

    @Test
    fun `init emit initial screen`() {
        viewModel.init()
        fakeCommunication.checkMapCalled()
        fakeCommunication.assertInitialScreenEmitted()
        functionsCallsStack.checkStack(1)
    }

    private interface FakeCommunication : NavigationCommunication.Mutable {

        fun assertInitialScreenEmitted()

        fun checkMapCalled()

        class Base(private val functionsCallsStack: FunctionsCallsStack) : FakeCommunication {

            private var emittedScreen: Screen? = null

            override fun assertInitialScreenEmitted() {
                assertEquals(InitialScreen, emittedScreen)
            }

            override fun checkMapCalled() {
                functionsCallsStack.checkCalled(MAP_CALLED)
            }

            override fun map(source: Screen) {
                emittedScreen = source
                functionsCallsStack.put(MAP_CALLED)
            }

            companion object {

                private const val MAP_CALLED = "MAP_CALLED"
            }
        }
    }
}