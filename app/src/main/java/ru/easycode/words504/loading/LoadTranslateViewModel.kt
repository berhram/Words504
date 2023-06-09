package ru.easycode.words504.loading

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.easycode.words504.loading.adapter.ItemTranslateUi
import ru.easycode.words504.loading.adapter.TranslateResultUi
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.translate.data.cloud.TranslateService

class LoadTranslateViewModel(
    private val service: TranslateService,
    private val dispatchers: DispatchersList,
    private val uiCommunication: Communication.Mutable<TranslateResultUi>
) : ViewModel(), Communication.Observe<TranslateResultUi> {

    private val mutex = Mutex()

    private val translatedList = mutableListOf<ItemTranslateUi>()

    private val textToTranslate =
        "Collins Example Sentences is a database of authentic English examples extracted " +
            "from Collins resources. Whilst a definition can help you understand the" +
            " meaning of a word, example sentences show you how to use the word in " +
            "a sentence. They can also help you check that you’ve understood the meaning " +
            "correctly, as well as enabling you to see and learn the linguistic features " +
            "of the word. If youre looking to improve your writing, example sentences " +
            "can also give you ideas for other words that naturally " +
            "occur in similar contexts."

    init {
        val wordsList = textToTranslate.split(" ")
        List(wordsList.size) { it }.map {
            viewModelScope.launch(dispatchers.io()) {
                val translated = service.translate("ru", wordsList[it]).execute().body()!!
                    .map(TranslateCloudToItemUiMapper(it, wordsList[it]))
                mutex.withLock {
                    translatedList.add(translated)
                    withContext(dispatchers.ui()) {
                        uiCommunication.map(
                            TranslateResultUi.Success(translatedList, wordsList.size)
                        )
                    }
                }
            }
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<TranslateResultUi>) {
        uiCommunication.observe(owner, observer)
    }
}
