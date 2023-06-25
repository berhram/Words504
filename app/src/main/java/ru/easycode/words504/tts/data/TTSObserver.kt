package ru.easycode.words504.tts.data

import ru.easycode.words504.tts.domain.TTSError

interface TTSObserver {

    fun started(phrase: String)

    fun finished(phrase: String)

    fun error(error: TTSError)
}
