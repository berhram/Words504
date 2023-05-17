package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import ru.easycode.words504.admintools.presentation.AdminActivity
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.loading.LoadTranslateActivity
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.recognition.presentation.TestVoiceRecognitionActivity

class MainActivity : BaseActivity<MainViewModel.Base>() {

    override val viewModelClass: Class<MainViewModel.Base> = MainViewModel.Base::class.java

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.coroutines -> {
            startActivity(Intent(this, LoadTranslateActivity::class.java))
            true
        }

        R.id.stt -> {
            startActivity(Intent(this, TestVoiceRecognitionActivity::class.java))
            true
        }

        R.id.admin -> {
            startActivity(Intent(this, AdminActivity::class.java))
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        viewModel.observe(this) { screen ->
            screen.navigate(supportFragmentManager, R.id.mainContainer)
        }
        if (savedInstanceState == null) {
            viewModel.init()
        }
    }
}
