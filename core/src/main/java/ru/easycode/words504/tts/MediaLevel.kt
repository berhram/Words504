package ru.easycode.words504.tts

import android.media.AudioManager

interface MediaLevel {

    fun isLowLevel(): Boolean

    class Base(
        private val manager: AudioManager,
        private val streamType: Int = AudioManager.STREAM_MUSIC
    ) : MediaLevel {

        override fun isLowLevel() = 30 >= 100 * manager.getStreamVolume(streamType) /
                manager.getStreamMaxVolume(streamType)
    }
}