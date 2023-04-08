package ru.easycode.words504

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import ru.easycode.words504.sl.ProvideViewModel

class App : Application(), ProvideViewModel {

    override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner)[clazz]
}
