package ru.easycode.words504.tts.data

interface TTSControlObserver {

    fun paused()

    fun resumed()

    fun stopped()
}
