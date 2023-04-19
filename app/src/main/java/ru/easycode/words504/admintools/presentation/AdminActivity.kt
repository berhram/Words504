package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import ru.easycode.words504.R
import ru.easycode.words504.presentation.BaseActivity

class AdminActivity : BaseActivity<AdminViewModel>() {
    override val viewModelClass: Class<AdminViewModel> = AdminViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        viewModel.observe(this) { ui ->
            ui.navigate(supportFragmentManager, R.id.admin_container)
        }
    }

    override fun onBackPressed() { }
}
