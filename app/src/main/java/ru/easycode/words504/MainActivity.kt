package ru.easycode.words504

import android.content.Intent
import android.os.Bundle
import ru.easycode.words504.admintools.presentation.AdminActivity
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.databinding.ActivityMainBinding
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen
import ru.easycode.words504.loading.LoadTranslateActivity
import ru.easycode.words504.presentation.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LanguagesCacheDataSource.Base(
            objectStorage = ObjectStorage.Base(
                Serialization.Base(),
                SimpleStorage.Base(ProvideSharedPreferences.Debug(applicationContext))
            )
        ).save(mutableListOf(LanguageCache.Base("ru","Russian"),LanguageCache.Base("ch","Chinese")))
        ChooseLanguageScreen.navigate(supportFragmentManager,R.id.container)
    }
}
