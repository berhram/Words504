package ru.easycode.words504.admintools.initial.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.admintools.presentation.AdminSentenceScreen
import ru.easycode.words504.databinding.FragmentAdminInitialBinding
import ru.easycode.words504.presentation.BaseFragment

/**
 * @author Asatryan on 02.05.2023
 */
class AdminInitialFragment : BaseFragment<AdminInitialViewModel, FragmentAdminInitialBinding>() {

    override val viewModelClass = AdminInitialViewModel::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAdminInitialBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goSentenceButton.setOnClickListener {
            viewModel.navigate(AdminSentenceScreen)
        }
    }
}
