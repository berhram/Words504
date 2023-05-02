package ru.easycode.words504.recognition.data

interface SpeechRecognizerCallback {
    fun started()
    fun finished(result: String)
    fun error(code: Int)
}
