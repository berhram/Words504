package ru.easycode.words504.loading.adapter

import ru.easycode.words504.domain.Mapper

interface ItemTranslateUi : Mapper<ItemTranslateUi, Boolean> {
    fun <T : Any> map(mapper: Base.Mapper<T>): T
    data class Base(
        private val id: Int,
        private val wordWithTranslate: String
    ) : ItemTranslateUi {

        interface Mapper<T : Any> {
            fun map(result: String): T
        }

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(wordWithTranslate)

        override fun map(source: ItemTranslateUi): Boolean = (source as Base).id == id
    }
}
