package ru.easycode.words504.sl

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.recognition.presentation.TestSTTViewModel

class BaseDependencyContainer(
    private val context: Context,
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule()
        TestSTTViewModel::class.java -> STTModule(context, core)
        else -> error.module(clazz)
    }
}
