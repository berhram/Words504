package ru.easycode.words504.tts.data

interface TTSQueue {

    interface Callback {

        fun speak(data: String)
    }

    interface Queue {

        fun done()

        fun processNext()

        fun clear()

        fun addAll(data: List<String>)

        fun init(callback: Callback)

        class Base : Queue {

            private val queue: MutableList<String> = ArrayList()
            lateinit var callback: Callback

            override fun done() {
                queue.removeFirst()
            }

            override fun processNext() {
                queue.firstOrNull()?.let {
                    callback.speak(it)
                }
            }

            override fun clear() {
                queue.clear()
            }

            override fun addAll(data: List<String>) {
                queue.addAll(data)
            }

            override fun init(callback: Callback) {
                this.callback = callback
            }
        }
    }
}
