package ru.easycode.words504.tts.data

interface TTSControlObserver {
    // TODO должны ли эти методы принмать какие -то значения. Например пауза на каком слове, восстановиль с какого
    fun paused()

    fun resumed()

    fun stopped()
}
