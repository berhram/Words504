package ru.easycode.words504.tts.presentation

import android.speech.tts.TextToSpeech
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.data.TTSCallback
import ru.easycode.words504.tts.data.TTSEngine

class TTSViewModelTest : BaseTest() {

    private lateinit var fakeCommunication: FakeCommunications
    private lateinit var viewModel: TTSViewModel
    private lateinit var fakeTTSEngine: FakeTTSEngine
    private lateinit var fakeMediaLevel: FakeMediaLevel
    private lateinit var fakeManageResource: FakeManageResource

    @Before
    fun setup() {
        fakeTTSEngine = FakeTTSEngine.Base(functionsCallsStack)
        fakeCommunication = FakeCommunications.Base()
        fakeManageResource = FakeManageResource.Base(functionsCallsStack, "Low volume!")
    }

    @Test
    fun success() {
        fakeMediaLevel = FakeMediaLevel.Base(functionsCallsStack, false)
        viewModel = TTSViewModel.Base(
            dispatchers = TestDispatchersList(),
            ttsEngine = fakeTTSEngine,
            resultCommunication = fakeCommunication,
            mediaLevel = fakeMediaLevel,
            manageResources = fakeManageResource
        )
        viewModel.init { }
        viewModel.ttsString("Lalala")
        fakeTTSEngine.assertInitCalled()
        fakeMediaLevel.assertIsLowLevelCalled()
        fakeTTSEngine.assertSpeakCalled()
        fakeCommunication.assertState(TTSState.Finished(""))
        functionsCallsStack.checkStack(3)
    }

    @Test
    fun `low volume`() {
        fakeMediaLevel = FakeMediaLevel.Base(functionsCallsStack, true)
        viewModel = TTSViewModel.Base(
            dispatchers = TestDispatchersList(),
            ttsEngine = fakeTTSEngine,
            resultCommunication = fakeCommunication,
            mediaLevel = fakeMediaLevel,
            manageResources = fakeManageResource
        )
        viewModel.init { }
        viewModel.ttsString("Lalala")
        fakeTTSEngine.assertInitCalled()
        fakeMediaLevel.assertIsLowLevelCalled()
        fakeManageResource.assertStringCalled()
        fakeTTSEngine.assertSpeakCalled()
        fakeCommunication.assertState(TTSState.Finished("Low volume!"))
        functionsCallsStack.checkStack(4)
    }

    private interface FakeTTSEngine : TTSEngine {

        fun assertInitCalled()

        fun assertSpeakCalled()

        class Base(
            private val functionsCallsStack: FunctionsCallsStack
        ) : FakeTTSEngine {

            private lateinit var callback: TTSCallback

            override fun init(initCallback: TextToSpeech.OnInitListener, ttsCallback: TTSCallback) {
                callback = ttsCallback
                functionsCallsStack.put(INIT_CALLED)
            }

            override fun speak(phrase: String) {
                callback.finished()
                functionsCallsStack.put(SPEAK_CALLED)
            }

            override fun assertInitCalled() {
                functionsCallsStack.checkCalled(INIT_CALLED)
            }

            override fun assertSpeakCalled() {
                functionsCallsStack.checkCalled(SPEAK_CALLED)
            }

            companion object {

                private const val INIT_CALLED = "init called"
                private const val SPEAK_CALLED = "speak called"
            }
        }
    }

    private interface FakeCommunications : TTSCommunication {

        fun assertState(state: TTSState)


        class Base : FakeCommunications {

            private var savedState: TTSState? = null

            override fun map(source: TTSState) {
                savedState = source
            }

            override fun assertState(state: TTSState) {
                assertEquals(state, savedState)
            }
        }
    }

    private interface FakeMediaLevel : MediaLevel {

        fun assertIsLowLevelCalled()

        class Base(
            private val functionsCallsStack: FunctionsCallsStack,
            private val isLowLevel: Boolean
        ) : FakeMediaLevel {

            override fun isLowLevel(): Boolean {
                functionsCallsStack.put(IS_LOW_LEVEL_CALLED)
                return isLowLevel
            }

            override fun assertIsLowLevelCalled() {
                functionsCallsStack.checkCalled(IS_LOW_LEVEL_CALLED)
            }

            companion object {

                private const val IS_LOW_LEVEL_CALLED = "is low level called"
            }
        }
    }

    private interface FakeManageResource : ManageResources {

        fun assertStringCalled()

        class Base(
            private val functionsCallsStack: FunctionsCallsStack,
            private val message: String
        ) : FakeManageResource {

            override fun assertStringCalled() {
                functionsCallsStack.checkCalled(STRING_CALLED)
            }

            override fun string(id: Int): String {
                functionsCallsStack.put(STRING_CALLED)
                return message
            }

            companion object {

                private const val STRING_CALLED = "string called"
            }
        }
    }
}
