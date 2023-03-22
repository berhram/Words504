package ru.easycode.words504.data.cloud

import ru.easycode.words504.BuildConfig
import ru.easycode.words504.data.cloud.retrofit.ProvideHeaderInterceptor

class AuthHeaderInterceptorProvider : ProvideHeaderInterceptor.Abstract(
    "Authorization",
    "DeepL-Auth-Key ${BuildConfig.DEEPL_API_KEY}"
)
