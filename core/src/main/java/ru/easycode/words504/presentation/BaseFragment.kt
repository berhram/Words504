package ru.easycode.words504.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import ru.easycode.words504.sl.ProvideViewModel

abstract class BaseFragment<T : ViewModel, V : ViewBinding> : Fragment() {

    private var _binding: V? = null

    protected lateinit var viewModel: T
    protected abstract val viewModelClass: Class<T>
    protected val binding get() = _binding!!

    protected abstract fun fragmentBinding(inflater: LayoutInflater, container: ViewGroup?): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(viewModelClass, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = fragmentBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
