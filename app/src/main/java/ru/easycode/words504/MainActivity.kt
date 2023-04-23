package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import ru.easycode.words504.admintools.presentation.AdminActivity
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.loading.LoadTranslateActivity
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.recognition.presentation.TestVoiceRecognitionActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testButton.setOnClickListener {
            val intent = Intent(this, TestVoiceRecognitionActivity::class.java)
            startActivity(intent)
        }

        binding.gotoAdminButton.setOnClickListener {
            startActivity(Intent(this, AdminActivity::class.java))
        }
        binding.gotoLoadCoroutines.setOnClickListener {
            startActivity(Intent(this, LoadTranslateActivity::class.java))
        }
    }
}
