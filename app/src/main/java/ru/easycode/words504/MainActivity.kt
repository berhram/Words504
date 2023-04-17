package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.rsrc.TestTranslateActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToTestActivity.setOnClickListener {
            val intent = Intent(this, TestTranslateActivity::class.java)
            startActivity(intent)
        }
    }
}
