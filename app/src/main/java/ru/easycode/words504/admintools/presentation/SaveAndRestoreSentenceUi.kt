package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import ru.easycode.words504.presentation.SaveAndRestore

interface SaveAndRestoreSentenceUi : SaveAndRestore<SentenceUi> {
    class Base(bundle: Bundle?) :
        SaveAndRestore.Abstract<SentenceUi>(
            bundle,
            "SaveAndRestoreSentenceUi",
            SentenceUi.Base::class.java
        ), SaveAndRestoreSentenceUi
}
