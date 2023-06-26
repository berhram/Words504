package ru.easycode.words504.tts.presentation

import ru.easycode.words504.tts.data.TTSEngine

interface TTSControlState {
    fun map(ttsEngine: TTSEngine)

    object Pause : TTSControlState {
        override fun map(ttsEngine: TTSEngine) {
            ttsEngine.pause()
        }
    }

    object Resume : TTSControlState {
        override fun map(ttsEngine: TTSEngine) {
            ttsEngine.resume()
        }
    }

    object Stop : TTSControlState {
        override fun map(ttsEngine: TTSEngine) {
            ttsEngine.stop()
        }
    }
}
