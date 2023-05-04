package ru.easycode.words504.tts.data

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri

interface MediaPlayerEngine {

    fun init()
    fun play()
    fun pause()
    fun stop()
    fun reset()

    class Base(private val context: Context) : MediaPlayerEngine {
        private lateinit var player: MediaPlayer
        private var counter = 0
        // todo будет использоваться для определения названия файла, потом поменять

        override fun init() {
            player = MediaPlayer.create(
                context,
                (context.filesDir.absolutePath + "/" + "$counter.wav").toUri()
            )
        }

        override fun play() {
            player.start()
        }

        override fun pause() {
            player.pause()
        }

        override fun stop() {
            player.stop()
            counter = 0
        }

        override fun reset() {
            player.release()
        }
    }
}
