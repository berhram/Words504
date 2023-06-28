package ru.easycode.words504.data.cache

interface DataBase<T : Any> {
    fun create(): T
    fun <S : Any, V : Any, C : GenericConverter<S, V>> create(typeConverter: C): T
}
