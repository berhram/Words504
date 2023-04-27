package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

interface TTSEngine {
    fun init(onInitListener: OnInitListener, callback : TTSCallback)
    fun speak(phrases: List<String>)
    fun speak(phrase: String)
    fun stop()
    fun shutdown()
//    fun setEndOfSpeechListener(callback: (phrase: String) -> Unit)
//    fun setPartialEndOfSpeechListener(callback: (phrase: String) -> Unit)
//    fun setStartOfSpeechListener(callback: (phrase: String) -> Unit)

    class Base(private val context: Context) : TTSEngine {

        private lateinit var tts: TextToSpeech
        private val queue: LinkedBlockingQueue<String> = LinkedBlockingQueue()
//        private var startSpeechCallback: (phrase: String) -> Unit = {}
//        private var endSpeechCallback: (phrase: String) -> Unit = {}
//        private var partialEndSpeechCallback: (phrase: String) -> Unit = {}
//
//        private val utteranceProgressListener = object : UtteranceProgressListener() {
//            override fun onStart(utteranceId: String?) {
//                startSpeechCallback.invoke(utteranceId ?: "")
//            }
//
//            override fun onDone(utteranceId: String?) {
//                queue.poll()
//                (if (queue.size > 0) partialEndSpeechCallback else endSpeechCallback).invoke(
//                    utteranceId ?: ""
//                )
//            }
//
//            override fun onError(utteranceId: String?) {
//            }
//
//            override fun onStop(utteranceId: String?, interrupted: Boolean) {
//                queue.clear()
//                endSpeechCallback.invoke(utteranceId ?: "")
//            }
//        }

        override fun init(onInitListener: OnInitListener, callback: TTSCallback) {
            tts = TextToSpeech(context, onInitListener)
            tts.setOnUtteranceProgressListener(object : SimpleTTSListener() {
                override fun onStart(p0: String?) {
                    if (p0 != null) callback.started(p0)
                    else callback.started("empty p0")
                }

                override fun onDone(p0: String?) {
                    queue.poll()
                    if (queue.size > 0) callback.partialFinished(p0 ?: "")
                    else callback.finished(p0 ?: "")

//                    if (p0 != null) callback.finished(p0)
//                    else callback.finished("empty p0")
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    if (utteranceId != null) callback.error(utteranceId, errorCode)
                    else callback.error("empty utteranceId", errorCode)
                }

                @Deprecated("Deprecated in Java")
                override fun onError(p0: String?) {
                    // TODO: обязательно нужно реализовать, но это депрекейт
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

//        override fun setEndOfSpeechListener(callback: (phrase: String) -> Unit) {
//            endSpeechCallback = callback
//        }

//        override fun setPartialEndOfSpeechListener(callback: (phrase: String) -> Unit) {
//            partialEndSpeechCallback = callback
//        }

//        override fun setStartOfSpeechListener(callback: (phrase: String) -> Unit) {
//            startSpeechCallback = callback
//        }
    }
}
