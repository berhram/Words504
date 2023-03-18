package ru.easycode.words504.tts

import android.content.Context

interface ProvideSystemService {

    fun <T: Any> provideService(context: Context): T

    abstract class Abstract<K: Any>(
        private val serviceName: String
    ) : ProvideSystemService {
        override fun <K: Any> provideService(context: Context): K =
            context.applicationContext.getSystemService(serviceName) as K
    }

    class AudioManager : Abstract<AudioManager>(Context.AUDIO_SERVICE)
}