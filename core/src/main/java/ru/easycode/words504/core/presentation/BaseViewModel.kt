package ru.easycode.words504.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseViewModel(
    private val dispatchers: DispatchersList
) : ViewModel() {
    protected fun <T> handle(io: suspend () -> T, ui: () -> Unit) =
        viewModelScope.launch(dispatchers.io()) {
            io.invoke()
            withContext(dispatchers.ui()) {
                ui.invoke()
            }
        }
}


