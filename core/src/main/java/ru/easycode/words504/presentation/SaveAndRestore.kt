package ru.easycode.words504.presentation

import android.os.Bundle
import java.io.Serializable
import ru.easycode.words504.data.Empty

interface SaveAndRestore<T : Serializable> : Empty {
    fun save(obj: T)
    fun restore(): T

    abstract class Abstract<T : Serializable>(
        private val bundle: Bundle?,
        private val key: String
    ) : SaveAndRestore<T> {

        override fun save(obj: T) = bundle!!.putSerializable(key, obj)

        override fun isEmpty(): Boolean = bundle == null
    }
}
