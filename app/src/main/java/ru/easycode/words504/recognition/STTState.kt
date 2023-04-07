package ru.easycode.words504.recognition

interface STTState {

    data class Started(private val state: String): STTState

    data class Finished(private val state: String): STTState

    data class Error(private val state: String): STTState

}
