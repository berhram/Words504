package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminFragment : BaseFragment<AdminViewModel>() {

    override val viewModelClass = AdminViewModel::class.java
    private val binding = FragmentAdminBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.fragmentAdmin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(this) { }
        binding.adminAddButton.setOnClickListener { }
        binding.adminSaveButton.setOnClickListener { }
    }
}
