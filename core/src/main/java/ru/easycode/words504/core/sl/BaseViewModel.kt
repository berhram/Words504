package ru.easycode.words504.core.sl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseViewModel(
    private val dispatchers: DispatchersList
) : ViewModel() {

    protected fun  handle(io: suspend () -> Unit, ui: () -> Unit, coroutineScope: CoroutineScope) =
        coroutineScope.launch(dispatchers.io()) {
            io.invoke()
            withContext(dispatchers.ui()) {
                ui.invoke()
            }
        }
}