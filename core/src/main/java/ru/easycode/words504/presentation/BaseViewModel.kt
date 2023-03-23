package ru.easycode.words504.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val dispatchers: DispatchersList
) : ViewModel() {
    protected fun <T : Any> handle(io: suspend () -> T, ui: (T) -> Unit) =
        viewModelScope.launch(dispatchers.io()) {
            val result = io.invoke()
            withContext(dispatchers.ui()) {
                ui.invoke(result)
            }
        }
}


