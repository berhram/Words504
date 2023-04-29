package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminFragment : BaseFragment<AdminViewModel>() {

    override val viewModelClass = AdminViewModel::class.java
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminFragmentToolbar.setOnClickListener { }
        binding.adminAddButton.setOnClickListener { binding.wordsLinearLayout.add() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
