package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.presentation.ManageResources

interface CoreModule : ProvideSharedPreferences, ManageResources {

    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        private val manageResources: ManageResources = ManageResources.Base(context)

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun string(id: Int): String = manageResources.string(id)
    }
}
