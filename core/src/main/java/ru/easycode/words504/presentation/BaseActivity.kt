package ru.easycode.words504.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import ru.easycode.words504.sl.ProvideViewModel

abstract class BaseActivity<T : ViewModel> : AppCompatActivity(), ProvideViewModel {

    protected lateinit var viewModel: T

    protected abstract val viewModelClass: Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModel(viewModelClass, this)
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).viewModel(clazz, owner)
}
