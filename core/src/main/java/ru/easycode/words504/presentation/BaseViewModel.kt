package ru.easycode.words504.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val dispatchers: DispatchersList
) : ViewModel(), Handle {

    override fun <T : Any> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ) = viewModelScope.launch(dispatchers.io()) {
        val result = block.invoke()
        withContext(dispatchers.ui()) {
            ui.invoke(result)
        }
    }
}

interface Handle {
    fun <T : Any> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ): Job
}
