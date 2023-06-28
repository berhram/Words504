package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.QuotePreview

class LessonQuoteCloudToPreviewMapper(
    private val mapper: SentenceCloud.Mapper<String>
) : LessonQuoteCloud.Mapper<QuotePreview.Base> {
    override fun map(value: List<SentenceCloud>, author: String): QuotePreview.Base {
        val quoteSentences = value.map { it.map(mapper) }
        return QuotePreview.Base(quoteSentences.joinToString(" "), author)
    }
}
