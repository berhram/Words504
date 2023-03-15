package ru.easycode.words504.core.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideViewModel {
    fun <T : ViewModel> provide(clazz: Class<T>, owner: ViewModelStoreOwner): T
}