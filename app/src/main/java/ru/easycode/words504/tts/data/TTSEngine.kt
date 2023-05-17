package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import java.io.File
import java.util.Locale
import java.util.concurrent.LinkedBlockingQueue

interface TTSEngine {
    fun init(onInitListener: OnInitListener, callback: TTSCallback)
    fun speak(phrases: List<String>)
    fun speak(phrase: String)
    fun stop()
    fun shutdown()
    fun makeSentenceMedia(phrase: String)
    fun makeSentenceMedia(phrases: List<String>)

    class Base(private val context: Context) : TTSEngine {

        private lateinit var tts: TextToSpeech
        private val queue: LinkedBlockingQueue<String> = LinkedBlockingQueue()
        private var counter = 0

        override fun init(onInitListener: OnInitListener, callback: TTSCallback) {
            tts = TextToSpeech(context, onInitListener)
            tts.setOnUtteranceProgressListener(object : SimpleTTSListener() {
                override fun onStart(p0: String?) {
                    if (p0 != null) {
                        callback.started(p0)
                    } else {
                        callback.started("empty p0")
                    }
                }

                override fun onDone(p0: String?) {
                    queue.poll()
                    if (queue.size > 0) {
                        callback.partialFinished(p0 ?: "")
                    } else {
                        callback.finished(p0 ?: "")
                    }
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    if (utteranceId != null) {
                        callback.error(utteranceId, errorCode)
                    } else {
                        callback.error("empty utteranceId", errorCode)
                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(p0: String?) {
                }

                override fun onStop(utteranceId: String?, interrupted: Boolean) {
                    if (utteranceId != null) callback.stopped(utteranceId, interrupted)
                    else callback.stopped("empty utteranceId", interrupted)
                }
            })
            tts.language = Locale.ENGLISH
        }

        override fun speak(phrases: List<String>) {
            stop()
            phrases.forEach { phrase ->
                if (phrase.isEmpty()) return
                queue.add(phrase)
                tts.speak(phrase, TextToSpeech.QUEUE_ADD, null, phrase)
            }
        }

        override fun speak(phrase: String) {
            stop()
            queue.add(phrase)
            tts.speak(phrase, TextToSpeech.QUEUE_ADD, null, phrase)
        }

        override fun stop() {
            tts.stop()
        }

        override fun shutdown() {
            tts.shutdown()
        }

        override fun makeSentenceMedia(phrase: String) {
            val filePath = context.filesDir.absolutePath + "/" + "$counter.wav"
            val file = File(filePath)
            counter++
            tts.synthesizeToFile(phrase.subSequence(0, phrase.lastIndex), null, file, filePath)
        }

        override fun makeSentenceMedia(phrases: List<String>) {
            TODO("Not yet implemented")
        }
    }
}
