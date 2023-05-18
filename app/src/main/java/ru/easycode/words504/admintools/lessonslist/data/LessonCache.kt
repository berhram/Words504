package ru.easycode.words504.admintools.lessonslist.data

import ru.easycode.words504.presentation.Screen

interface LessonCache {
    // todo refactor this when model will done
    interface Mapper<T> {
        fun map(id: String, isComplete: Boolean, lastEditScreen: Screen): T
    }

    fun <T> map(mapper: Mapper<T>): T

    class Complete : Mapper<Boolean> {
        override fun map(id: String, isComplete: Boolean, lastEditScreen: Screen) = isComplete
    }

    class LastEditScreen : Mapper<Screen> {
        override fun map(id: String, isComplete: Boolean, lastEditScreen: Screen) = lastEditScreen
    }
}
