package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import ru.easycode.words504.presentation.SaveAndRestore

class SaveAndRestoreSentenceUi(
    bundle: Bundle?
) : SaveAndRestore.Abstract<SentenceUi.Base>(bundle, "SaveAndRestoreSentenceUi") {
    override val clazz = SentenceUi.Base::class.java
}
