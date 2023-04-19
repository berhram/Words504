package ru.easycode.words504.loading

import androidx.lifecycle.MutableLiveData
import ru.easycode.words504.loading.adapter.TranslateResultUi
import ru.easycode.words504.presentation.Communication

class LoadTranslateCommunication : Communication.Abstract<TranslateResultUi>(MutableLiveData())
