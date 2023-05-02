package ru.easycode.words504.languages.presentation

import android.os.Bundle
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.presentation.SaveAndRestore


interface SaveAndRestoreLanguageCache : SaveAndRestore<LanguageCache> {

    class Base(bundle: Bundle?) :
        SaveAndRestore.Abstract<LanguageCache>(
            bundle,
            "SaveAndRestoreLanguageCache",
            LanguageCache.Base::class.java
        ),
        SaveAndRestoreLanguageCache
}
