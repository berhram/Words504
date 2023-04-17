package ru.easycode.words504

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import ru.easycode.words504.admintools.sl.AdminDependencyContainer
import ru.easycode.words504.sl.BaseDependencyContainer
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.ProvideViewModel
import ru.easycode.words504.sl.ViewModelsFactory

class App : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory


    override fun onCreate() {
        super.onCreate()
        val coreModule = CoreModule.Base(this, BuildConfig.DEBUG)
        viewModelsFactory = ViewModelsFactory(
            BaseDependencyContainer(
                coreModule,
                AdminDependencyContainer(coreModule)
            )
        )
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}
