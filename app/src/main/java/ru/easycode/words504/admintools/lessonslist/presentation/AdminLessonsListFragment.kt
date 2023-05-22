package ru.easycode.words504.admintools.initial.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.easycode.words504.admintools.core.presentation.Choose
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
        binding.sentenceButton.setOnClickListener {
            viewModel.navigate(AdminSentenceScreen)
        }
        binding.customViewGroup.init(
            listOf(
                Choose.Base("1", "type1"),
                Choose.Base("2", "type2"),
                Choose.Base("3", "type3"),
                Choose.Base("4", "type4"),
                Choose.Base("5", "type5"),
                Choose.Base("6", "type6"),
                Choose.Base("7", "type7"),
                Choose.Base("8", "type8"),
                Choose.Base("9", "type9"),
                Choose.Base("10", "type10")
            )
        ) {
            Toast.makeText(requireContext(), "Chosen $it", Toast.LENGTH_LONG).show()
        }
    }
}
