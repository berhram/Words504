package ru.easycode.words504.tts.sl

import android.content.Context
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.presentation.TTSTestFragmentUiCommunication
import ru.easycode.words504.tts.presentation.TTSTestFragmentViewModel

class TTSTestModule(private val coreModule: CoreModule,
 private val context: Context) : Module<TTSTestFragmentViewModel.Base> {
    override fun viewModel(): TTSTestFragmentViewModel.Base {
        return TTSTestFragmentViewModel.Base(
            ttsCommunication = coreModule.provideTTSCommunication(),
            ttsObserversStorage = coreModule.provideTTSObserversStorage(),
            ttsObserverControlStorage = coreModule.provideTTSControlObserversStorage(),
            ttsControlCommunication = coreModule.provideTTSControlCommunication(),
            uiStateCommunication = TTSTestFragmentUiCommunication.Base(),
            manageResources = coreModule,
            dispatchers = coreModule.provideDispatchers(),
            mediaLevel = MediaLevel.Base(context)
        )
    }
}
