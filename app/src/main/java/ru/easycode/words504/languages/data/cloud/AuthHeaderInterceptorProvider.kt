package ru.easycode.words504.languages.data.cloud

import okhttp3.Interceptor
import ru.easycode.words504.data.cloud.HeaderInterceptor
import ru.easycode.words504.data.cloud.ProvideInterceptor

class AuthHeaderInterceptorProvider : ProvideInterceptor {
    override fun interceptor(): Interceptor {
        return HeaderInterceptor(
            "Authorization",
            "DeepL-Auth-Key $DEEPL_API_KEY"
        )
    }

    companion object {
        private const val DEEPL_API_KEY = "d465c6ca-4040-c280-c1c0-033bdeb53950:fx"
    }
}
