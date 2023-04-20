package ru.easycode.words504.languages.presentation

import android.os.Bundle
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.presentation.SaveAndRestore

class SaveAndRestoreLanguageCache(
    bundle: Bundle?,
) : SaveAndRestore.Abstract<LanguageCache.Base>(bundle, "SaveAndRestoreLanguageCache") {
    override val clazz = LanguageCache.Base::class.java
}
