package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.WordsPreview

interface LessonWordsCloudToWordPreviewMapper<T : Any> {
    fun map(words: List<LessonWordCloud>): T

    class Base(
        private val mapper: LessonWordCloud.Mapper<String>
    ) : LessonWordsCloudToWordPreviewMapper<WordsPreview> {
        override fun map(words: List<LessonWordCloud>): WordsPreview =
            WordsPreview.Base(words.map { (it.map(mapper)) })
    }
}

class LessonWordCloudToUi : LessonWordCloud.Mapper<String> {
    override fun map(
        id: String,
        definitions: List<SentenceCloud>,
        examples: List<SentenceCloud>
    ): String = id
}
