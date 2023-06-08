package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import ru.easycode.words504.R
import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListScreen
import ru.easycode.words504.databinding.ActivityAdminBinding
import ru.easycode.words504.presentation.BaseActivity

class AdminActivity : BaseActivity<AdminActivityViewModel.Base>() {
    override val viewModelClass = AdminActivityViewModel.Base::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.observe(this) {
            it.navigate(supportFragmentManager, binding.adminContainer.id)
            it.showTitle(supportActionBar!!)
        }
        if (savedInstanceState == null) {
            viewModel.navigate(AdminLessonsListScreen(getString(R.string.admin_lessons)))
        }
    }
}
