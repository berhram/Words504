package ru.easycode.words504.admintools.lessonquote.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminQuoteBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminLessonQuoteFragment :
    BaseFragment<AdminLessonQuoteViewModel, FragmentAdminQuoteBinding>() {
    // TODO
    override val viewModelClass: Class<AdminLessonQuoteViewModel> =
        AdminLessonQuoteViewModel::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAdminQuoteBinding.inflate(inflater, container, false)
}
