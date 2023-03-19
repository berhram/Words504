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
            context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        }

        override fun isLowLevel() = 30 >= 100 * manager.getStreamVolume(streamType) /
                manager.getStreamMaxVolume(streamType)
    }
}