package ru.easycode.words504.tts.presentation

import android.speech.tts.TextToSpeech
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen
import ru.easycode.words504.tts.data.TTSEngine
import ru.easycode.words504.tts.data.TTSObserver

class TTSViewModelTest : BaseTest() {

    private lateinit var viewModel: TTSViewModel
    private lateinit var fakeTTSEngine: FakeTTSEngine
    private lateinit var fakeTTSControlCommunication: FakeTTSControlCommunication
    private lateinit var fakeNavigationCommunication: FakeNavigationCommunication
    private lateinit var fakeTTSCommunication: FakeTTSCommunication

    @Before
    fun setup() {
        fakeTTSEngine = FakeTTSEngine.Base(functionsCallsStack)
        fakeTTSControlCommunication = FakeTTSControlCommunication.Base()
        fakeTTSCommunication = FakeTTSCommunication.Base()
        fakeNavigationCommunication = FakeNavigationCommunication.Base()
    }

    @Test
    fun speak() {
        viewModel = TTSViewModel.Base(
            dispatchers = TestDispatchersList(),
            ttsEngine = fakeTTSEngine,
            ttsCommunication = fakeTTSCommunication,
            ttsControlCommunication = fakeTTSControlCommunication,
            navigationCommunication = fakeNavigationCommunication
        )
        viewModel.init { }
        viewModel.speak(listOf("Lala", "Jaja"))
        fakeTTSEngine.assertInitCalled()
        fakeTTSEngine.assertSpeakCalled()
        functionsCallsStack.checkStack(2)
    }

    @Test
    fun changePlayback() {
        viewModel = TTSViewModel.Base(
            dispatchers = TestDispatchersList(),
            ttsEngine = fakeTTSEngine,
            ttsCommunication = fakeTTSCommunication,
            ttsControlCommunication = fakeTTSControlCommunication,
            navigationCommunication = fakeNavigationCommunication
        )
        viewModel.init { }
        viewModel.changePlayback()
        fakeTTSEngine.assertInitCalled()
        fakeTTSEngine.assertChangePlaybackCalled()
        functionsCallsStack.checkStack(2)
    }

    private interface FakeTTSEngine : TTSEngine {

        fun assertInitCalled()

        fun assertSpeakCalled()

        fun assertChangePlaybackCalled()

        class Base(

            private val functionsCallsStack: FunctionsCallsStack
        ) : FakeTTSEngine {

            override fun init(initCallback: TextToSpeech.OnInitListener) {
                functionsCallsStack.put(INIT_CALLED)
            }

            override fun addObserver(observer: TTSObserver) {
                functionsCallsStack.put(ADD_OBSERVER_CALLED)
            }

            override fun changePlayback() {
                functionsCallsStack.put(CHANGE_PLAYBACK_CALLED)
            }

            override fun speak(phrases: List<String>) {
                functionsCallsStack.put(SPEAK_CALLED)
            }

            override fun assertChangePlaybackCalled() {
                functionsCallsStack.checkCalled(CHANGE_PLAYBACK_CALLED)
            }

            override fun assertInitCalled() {
                functionsCallsStack.checkCalled(INIT_CALLED)
            }

            override fun assertSpeakCalled() {
                functionsCallsStack.checkCalled(SPEAK_CALLED)
            }

            companion object {
                private const val ADD_OBSERVER_CALLED = "add observer called"
                private const val CHANGE_PLAYBACK_CALLED = "changed playback called"
                private const val INIT_CALLED = "init called"
                private const val SPEAK_CALLED = "speak called"
            }
        }
    }


    private interface FakeTTSCommunication : TTSCommunication.Observe {

        class Base : FakeTTSCommunication {

            override fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) = Unit
        }
    }

    private interface FakeTTSControlCommunication : TTSControlCommunication.Observe {

        class Base : FakeTTSControlCommunication {

            override fun observe(owner: LifecycleOwner, observer: Observer<Unit>) = Unit
        }
    }

    private interface FakeNavigationCommunication : NavigationCommunication.Mutable {


        class Base : FakeNavigationCommunication {

            override fun map(source: Screen) = Unit

            override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) = Unit
        }
    }


}
