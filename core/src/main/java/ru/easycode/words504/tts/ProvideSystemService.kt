package ru.easycode.words504.tts

import android.content.Context

interface ProvideSystemService {

    fun <T : Any> provideService(): T

    abstract class Abstract(
        private val context: Context,
        private val serviceName: String,
    ) : ProvideSystemService {

        private val service by lazy {
            context.applicationContext.getSystemService(serviceName)
        }

        override fun <K : Any> provideService(): K = service as K
    }

    class AudioManager(private val context: Context) : Abstract(context, Context.AUDIO_SERVICE)
}