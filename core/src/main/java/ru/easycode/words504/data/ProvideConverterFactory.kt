package ru.easycode.words504.data

import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

interface ProvideConverterFactory {

    fun converterFactory(): Converter.Factory

    class Base : ProvideConverterFactory {

        override fun converterFactory(): Converter.Factory = GsonConverterFactory.create()
    }
}