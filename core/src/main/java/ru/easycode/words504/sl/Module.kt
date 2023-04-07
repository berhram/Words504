package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.BaseViewModel

interface Module<T : ViewModel> {
    fun viewModel(): T
    abstract class Abstract<T : BaseViewModel> : Module<T>
}
