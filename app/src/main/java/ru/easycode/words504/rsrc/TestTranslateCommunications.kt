package ru.easycode.words504.rsrc

import androidx.lifecycle.MutableLiveData
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.rsrc.adapter.TranslateResultUi

class TestTranslateCommunications : Communication.Abstract<TranslateResultUi>(MutableLiveData())
