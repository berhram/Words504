package ru.easycode.words504.tts.sl

import android.content.Context
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.data.TTSEngine
import ru.easycode.words504.tts.presentation.TTSStateCommunication
import ru.easycode.words504.tts.presentation.TTSViewModel

class TTSModule(private val core: CoreModule, private val context: Context) :
    Module<TTSViewModel.Base> {

    override fun viewModel(): TTSViewModel.Base = TTSViewModel.Base(
        dispatchers = core.provideDispatchers(),
        ttsEngine = TTSEngine.Base(context, core.provideTTSObserversStorage()),
        resultCommunication = TTSStateCommunication.Base(),
        mediaLevel = MediaLevel.Base(context),
        manageResources = ManageResources.Base(context),
        ttsCommunication = core.provideTTSCommunication(),
        navigationCommunication = NavigationCommunication.Base()
    )
}
