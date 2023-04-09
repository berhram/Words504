package ru.easycode.words504.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import ru.easycode.words504.sl.ProvideViewModel

abstract class BaseFragment<T : ViewModel> : Fragment() {

    protected lateinit var viewModel: T

    protected abstract fun viewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(viewModelClass(), this)
    }
}
