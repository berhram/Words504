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

        override fun isLowLevel() = THRESHOLD >= 100 * manager.getStreamVolume(streamType) /
                manager.getStreamMaxVolume(streamType)

        companion object  {
            private const val THRESHOLD = 30
        }
    }
}