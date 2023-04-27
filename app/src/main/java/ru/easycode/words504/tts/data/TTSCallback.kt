package ru.easycode.words504.tts.data

interface TTSCallback {
    fun started(utteranceId: String)
    fun finished(utteranceId: String)
    fun partialFinished(utteranceId: String)
    fun error(utteranceId: String, errorCode: Int)
    fun stopped(utteranceId: String, interrupted: Boolean)
}
