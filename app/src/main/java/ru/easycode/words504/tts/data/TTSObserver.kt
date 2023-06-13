package ru.easycode.words504.tts.data

interface TTSObserver {

    fun started(phrase: String)

    fun processed(phrase: String)

    fun finished(phrase: String)
}
