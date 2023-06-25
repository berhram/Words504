package ru.easycode.words504.tts.data

interface TTSObserversStorage {
    fun notify(block: (TTSObserver) -> Unit)
    fun add(ttsObserver: TTSObserver)
    fun remove(ttsObserver: TTSObserver)

    object Base : TTSObserversStorage {

        private val observers: MutableList<TTSObserver> = mutableListOf()

        override fun notify(block: (TTSObserver) -> Unit) {
            observers.forEach(block)
        }

        override fun add(ttsObserver: TTSObserver) {
            observers.add(ttsObserver)
        }

        override fun remove(ttsObserver: TTSObserver) {
            observers.remove(ttsObserver)
        }
    }
}
