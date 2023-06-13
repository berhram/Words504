package ru.easycode.words504.admintools.reviewLessonContent.mappers

import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.TextPreview

class LessonTextCloudToPreviewMapper(
    private val mapper: SentenceCloud.Mapper<String>
) : LessonTextCloud.Mapper<TextPreview.Base> {
    override fun map(title: SentenceCloud, sentences: List<SentenceCloud>): TextPreview.Base {
        val textString = sentences.joinToString(" ") { it.map(mapper) }
        val titleString = title.map(mapper)
        return TextPreview.Base(title = titleString, text = textString)
    }
}
