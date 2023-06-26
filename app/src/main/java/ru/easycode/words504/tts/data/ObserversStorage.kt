package ru.easycode.words504.tts.data

interface ObserversStorage<T : Any> {
    fun notify(block: (T) -> Unit)
    fun add(observer: T)
    fun remove(observer: T)

    class Base<T : Any> : ObserversStorage<T> {

        private val observers: MutableList<T> = mutableListOf()

        override fun notify(block: (T) -> Unit) {
            observers.forEach(block)
        }

        override fun add(observer: T) {
            observers.add(observer)
        }

        override fun remove(observer: T) {
            observers.remove(observer)
        }
    }
}
