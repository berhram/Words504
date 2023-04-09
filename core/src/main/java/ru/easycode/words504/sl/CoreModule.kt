package ru.easycode.words504.sl

import android.content.Context
import android.content.SharedPreferences
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.storage.Storage

interface CoreModule : ProvideSharedPreferences, Storage {

    class Core(
        private val context: Context,
        private val isDebug: Boolean
    ) : CoreModule {

        private val manageResources = context.resources

        override fun sharedPreferences(): SharedPreferences {
            TODO("Not yet implemented")
        }

        override fun read(key: String, default: String): String {
            TODO("Not yet implemented")
        }

        override fun <T : Any> read(key: String, default: T): T {
            TODO("Not yet implemented")
        }

        override fun save(key: String, value: String) {
            TODO("Not yet implemented")
        }

        override fun save(key: String, obj: Any) {
            TODO("Not yet implemented")
        }
    }
}
