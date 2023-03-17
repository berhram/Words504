package ru.easycode.words504.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseViewModel(
    private val dispatchers: DispatchersList
) : ViewModel() {
    protected fun <T> handle(io: suspend () -> T, ui: (result: T) -> Unit) =
        viewModelScope.launch(dispatchers.io()) {
            val result = io.invoke()
            withContext(dispatchers.ui()) {
                ui.invoke(result)
            }
        }
}


