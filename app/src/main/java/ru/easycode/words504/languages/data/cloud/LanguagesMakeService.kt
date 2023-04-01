package ru.easycode.words504.languages.data.cloud

import ru.easycode.words504.data.cloud.MakeService
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder

class LanguagesMakeService(retrofitBuilder: ProvideRetrofitBuilder) :
    MakeService.Abstract(retrofitBuilder, "https://api-free.deepl.com/v2/")
