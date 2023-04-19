package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.loading.LoadTranslateActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToLoadCoroutines.setOnClickListener {
            val intent = Intent(this, LoadTranslateActivity::class.java)
            startActivity(intent)
        }
    }
}
