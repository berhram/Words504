package ru.easycode.words504.presentation

import android.os.Build
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
        protected abstract val clazz: Class<T>

        override fun save(obj: T) = bundle!!.putSerializable(key, obj)

        override fun restore() =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle!!.getSerializable(key, clazz) as T
            } else {
                @Suppress("DEPRECATION")
                bundle!!.getSerializable(key) as T
            }

        override fun isEmpty(): Boolean = bundle == null
    }
}
