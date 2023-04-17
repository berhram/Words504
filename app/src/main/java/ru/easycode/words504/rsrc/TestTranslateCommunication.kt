package ru.easycode.words504.rsrc

import androidx.lifecycle.MutableLiveData
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.rsrc.adapter.TranslateResultUi

class TestTranslateCommunication : Communication.Abstract<TranslateResultUi>(MutableLiveData())
