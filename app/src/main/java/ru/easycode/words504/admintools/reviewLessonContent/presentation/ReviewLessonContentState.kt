package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.domain.Mapper

interface ReviewLessonContentState {
    fun map(adapter: Mapper.Unit<List<ReviewLessonContentUi>>)

    data class Initial(private val list: List<ReviewLessonContentUi>) : ReviewLessonContentState {
        override fun map(adapter: Mapper.Unit<List<ReviewLessonContentUi>>) = adapter.map(list)
    }
}
