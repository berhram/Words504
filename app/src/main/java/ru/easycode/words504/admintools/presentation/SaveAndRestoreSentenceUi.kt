package ru.easycode.words504.admintools.presentation

import android.os.Build
import android.os.Bundle
import ru.easycode.words504.presentation.SaveAndRestore

class SaveAndRestoreSentenceUi(
    private val key: String = "SaveAndRestoreSentenceUi",
    private val bundle: Bundle?
) : SaveAndRestore.Abstract<SentenceUi>(bundle, key) {

    override fun restore() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle!!.getSerializable(key, SentenceUi::class.java) as SentenceUi
    } else {
        @Suppress("DEPRECATION") bundle!!.getSerializable(key) as SentenceUi
    }
}
