package ru.easycode.words504.rsrc.adapter

interface TranslateResultUi {

    fun current(): Int
    fun currentList(): List<String>
    fun maximum(): Int

    class Base(
        private val currentList: List<String>,
        private val maximumSize: Int
    ) : TranslateResultUi {
        override fun current(): Int = currentList.size
        override fun currentList(): List<String> = currentList
        override fun maximum(): Int = maximumSize
    }
}
