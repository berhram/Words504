package ru.easycode.words504.admintools.lessonslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminInitialBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminLessonsListFragment :
    BaseFragment<AdminLessonsListViewModel.Base, FragmentAdminInitialBinding>() {

    override val viewModelClass = AdminLessonsListViewModel.Base::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAdminInitialBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo
    }
}
