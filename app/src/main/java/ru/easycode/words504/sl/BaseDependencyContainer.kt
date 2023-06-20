package ru.easycode.words504.sl

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.initial.presentation.InitialViewModel
import ru.easycode.words504.initial.sl.InitialModule
import ru.easycode.words504.languages.presentation.ChooseLanguageViewModel
import ru.easycode.words504.languages.sl.ChooseLanguageModule
import ru.easycode.words504.loading.LoadCoroutinesModule
import ru.easycode.words504.loading.LoadTranslateViewModel
import ru.easycode.words504.recognition.presentation.TestSTTViewModel
import ru.easycode.words504.tts.presentation.TTSTestFragmentViewModel
import ru.easycode.words504.tts.presentation.TTSViewModel
import ru.easycode.words504.tts.sl.TTSModule
import ru.easycode.words504.tts.sl.TTSTestModule

class BaseDependencyContainer(
    private val context: Context,
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        LoadTranslateViewModel::class.java -> LoadCoroutinesModule(core)
        MainViewModel.Base::class.java -> MainModule(core)
        ChooseLanguageViewModel.Base::class.java -> ChooseLanguageModule(core)
        TestSTTViewModel::class.java -> STTModule(context, core)
        InitialViewModel::class.java -> InitialModule(core)
        TTSViewModel.Base::class.java -> TTSModule(core, context)
        TTSTestFragmentViewModel.Base::class.java -> TTSTestModule(core)
        else -> error.module(clazz)
    }
}
