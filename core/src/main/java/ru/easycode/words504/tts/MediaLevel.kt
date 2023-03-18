package ru.easycode.words504.tts

import android.media.AudioManager

interface MediaLevel {

    fun currentLevel(): Int

    fun maxLevel(): Int

    fun isLowLevel(lowLevelPercentage: Int = 30): Boolean

    class Base(
        private val manager: AudioManager,
        private val streamType: Int = AudioManager.STREAM_MUSIC,
    ) : MediaLevel {
        override fun currentLevel() = manager.getStreamVolume(streamType)

        override fun maxLevel() = manager.getStreamMaxVolume(streamType)

        override fun isLowLevel(lowLevelPercentage: Int) =
            lowLevelPercentage >= 100 * currentLevel() / maxLevel()
    }
}