package ru.easycode.words504.rsrc

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.easycode.words504.languages.data.cloud.TranslateCloud
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList

interface RunBunchCoroutines :  Communication.Observe<TranslateCloud> {
    fun start(coroutineScope: CoroutineScope, textSize: Int, block: suspend () -> TranslateCloud)

    class Base(
        private val dispatchers: DispatchersList,
        private val translate: Communication.Mutable<TranslateCloud>,
        private val progress: ShowProgress
    ) : RunBunchCoroutines  {

        override fun start(
            coroutineScope: CoroutineScope,
            textSize: Int,
            block: suspend () -> TranslateCloud
        ) {
            List(textSize) { it }.map {
                coroutineScope.launch(dispatchers.io()) {
                    translate.map(block.invoke())
                    progress.show(textSize)
                }
            }
        }
        override fun observe(owner: LifecycleOwner, observer: Observer<TranslateCloud>) =
            translate.observe(owner, observer)
    }
}

interface ShowProgress : Communication.Observe<Int>  {
    suspend fun show(textSize: Int)

    class Base(
        private val dispatchers: DispatchersList,
        private val progress: Communication.Mutable<Int>
    ) : ShowProgress{

        private var count = 0

        private val mutex = Mutex()
        override suspend fun show(textSize: Int) {
            mutex.withLock {
                count++
                val currentProgress = (count * 100) / textSize
                withContext(dispatchers.ui()) {
                    progress.map(currentProgress)
                }
            }
        }
        override fun observe(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)
    }
}
