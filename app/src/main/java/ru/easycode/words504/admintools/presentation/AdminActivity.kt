package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import ru.easycode.words504.databinding.ActivityAdminBinding
import ru.easycode.words504.presentation.BaseActivity


class AdminActivity : BaseActivity<AdminViewModel>() {
    override val viewModelClass: Class<AdminViewModel> = AdminViewModel::class.java
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observe(this) {
            it.navigate(supportFragmentManager, binding.adminContainer.id)
        }
    }

    override fun onBackPressed() = Unit
}
