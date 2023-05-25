package ru.easycode.words504.admintools.lessonslist.data

interface LessonCache {
    // todo refactor this when model will done
    interface Mapper<T> {
        fun map(id: String, isComplete: Boolean): T
    }

    fun <T> map(mapper: Mapper<T>): T
}
