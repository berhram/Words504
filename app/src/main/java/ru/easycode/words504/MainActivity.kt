package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import ru.easycode.words504.admintools.presentation.AdminActivity
import ru.easycode.words504.presentation.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gotoAdminButton: Button = findViewById(R.id.gotoAdminButton)
        gotoAdminButton.setOnClickListener {
            startActivity(Intent(this, AdminActivity::class.java))
        }
    }
}
