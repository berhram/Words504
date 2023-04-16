package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideAdminViewModel {
    fun <T : ViewModel> adminViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T
}
