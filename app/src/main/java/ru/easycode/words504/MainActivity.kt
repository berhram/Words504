package ru.easycode.words504

import android.os.Bundle
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen
import ru.easycode.words504.presentation.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<LanguageCache.Base>()
        list.add(LanguageCache.Base("ru", "Russian"))
        list.add(LanguageCache.Base("ch", "Chinese"))
        val base = LanguagesCacheDataSource.Base(
            objectStorage = ObjectStorage.Base(
                Serialization.Base(),
                SimpleStorage.Base(ProvideSharedPreferences.Debug(applicationContext))
            )
        )
        base.save(list)
        if (savedInstanceState == null)
            ChooseLanguageScreen.navigate(supportFragmentManager, R.id.container)
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
