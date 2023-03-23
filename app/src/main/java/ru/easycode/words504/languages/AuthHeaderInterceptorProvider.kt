package ru.easycode.words504.languages

import okhttp3.Interceptor
import ru.easycode.words504.BuildConfig
import ru.easycode.words504.data.cloud.HeaderInterceptor
import ru.easycode.words504.data.cloud.ProvideInterceptor

class AuthHeaderInterceptorProvider : ProvideInterceptor {
    override fun interceptor(): Interceptor {
        return HeaderInterceptor(
            "Authorization",
            "DeepL-Auth-Key ${BuildConfig.DEEPL_API_KEY}"
        )
    }
}
