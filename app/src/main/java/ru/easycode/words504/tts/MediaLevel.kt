package ru.easycode.words504.tts

import android.content.Context
import android.media.AudioManager

interface MediaLevel {

    fun isLowLevel(): Boolean

    class Base(
        private val context: Context,
        private val streamType: Int = AudioManager.STREAM_MUSIC
    ) : MediaLevel {

        private val manager by lazy {
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        }

        override fun isLowLevel(): Boolean {
            val maxLevel = manager.getStreamMaxVolume(streamType)
            val currentLevel = manager.getStreamVolume(streamType)

            return THRESHOLD >= HUNDRED_PERCENT * currentLevel / maxLevel
        }

        companion object {
            private const val THRESHOLD = 30
            private const val HUNDRED_PERCENT = 100
        }
    }
}
