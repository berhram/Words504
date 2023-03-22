package ru.easycode.words504.data.cloud

import okhttp3.Interceptor
import ru.easycode.words504.BuildConfig
import ru.easycode.words504.data.cloud.retrofit.HeaderInterceptor
import ru.easycode.words504.data.cloud.retrofit.ProvideInterceptor

class AuthHeaderInterceptorProvider : ProvideInterceptor {
    override fun interceptor(): Interceptor {
        return HeaderInterceptor(
            "Authorization",
            "DeepL-Auth-Key ${BuildConfig.DEEPL_API_KEY}"
        )
    }
}
