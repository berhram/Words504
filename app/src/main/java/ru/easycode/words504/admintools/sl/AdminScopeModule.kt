package ru.easycode.words504.admintools.sl

import android.content.Context
import androidx.room.Room
import ru.easycode.words504.admintools.core.cache.AdminDataBase
import ru.easycode.words504.admintools.data.AdminToolsSharedPreferences
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonConverters
import ru.easycode.words504.admintools.presentation.SentenceUiCache
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.sl.ProvideNavigation
import ru.easycode.words504.sl.ProvideObjectStorage
import ru.easycode.words504.sl.ProvideSimpleStorage

interface AdminScopeModule :
    ProvideNavigation,
    ProvideSentenceUiCache,
    ProvideSimpleStorage,
    ProvideObjectStorage,
    ProvideSharedPreferences,
    ProvideAdminDatabase {

    class Base(context: Context) : AdminScopeModule {

        private val navigationCommunication = NavigationCommunication.Base()

        private val sentenceUiCache = SentenceUiCache.Base()

        private val sharedPref = AdminToolsSharedPreferences(context)

        private val simpleStorage = SimpleStorage.Base(sharedPref)

        private val objectStorage = ObjectStorage.Base(
            Serialization.Base(),
            simpleStorage
        )

        private val dataBase by lazy {
            return@lazy Room.databaseBuilder(
                context.applicationContext,
                AdminDataBase.Base::class.java,
                "admin_database"
            )
                .fallbackToDestructiveMigration()
                .addTypeConverter(LessonConverters.Base(Serialization.Base()))
                .build()
        }

        override fun provideNavigation() = navigationCommunication

        override fun provideSentenceUiCache() = sentenceUiCache

        override fun provideSimpleStorage() = simpleStorage

        override fun provideObjectStorage() = objectStorage

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun provideAdminDatabase() = dataBase
    }
}
