package ru.easycode.words504.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ru.easycode.words504.core.Mapper

interface Communication {

    interface Observe<T : Any> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Update<T : Any> : Mapper.Unit<T>

    interface Mutable<T : Any> : Observe<T>, Update<T>

    abstract class Abstract<T : Any>(
        private val liveData: MutableLiveData<T> = MutableLiveData(),
    ) : Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
}