package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T
}
