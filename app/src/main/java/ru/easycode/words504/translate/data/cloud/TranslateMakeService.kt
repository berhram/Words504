package ru.easycode.words504.translate.data.cloud

import ru.easycode.words504.data.cloud.MakeService
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder

class TranslateMakeService(retrofitBuilder: ProvideRetrofitBuilder) :
    MakeService.Abstract(retrofitBuilder, "https://api-free.deepl.com/v2/")
