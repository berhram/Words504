package ru.easycode.words504.data

import com.google.gson.Gson

interface JSONConvertable {
    fun toJson(): String = Gson().toJson(this)


}