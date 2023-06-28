package ru.easycode.words504.data.cache

interface GenericConverter<S : Any, V : Any> {
    fun fromObject(obj: S): V
    fun toObject(value: V): S
}
