package ru.easycode.words504.core

interface Mapper<S:Any, R:Any> {

    fun map(source: S): R

    interface Unit<S : Any>: Mapper<S, kotlin.Unit>
}