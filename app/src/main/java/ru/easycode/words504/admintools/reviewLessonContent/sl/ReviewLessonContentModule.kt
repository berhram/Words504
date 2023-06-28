package ru.easycode.words504.admintools.reviewLessonContent.sl

import ru.easycode.words504.admintools.data.cloud.SentenceCloud
import ru.easycode.words504.admintools.data.cloud.WordCloud
import ru.easycode.words504.admintools.lessonslist.data.LessonsListRepositoryBase
import ru.easycode.words504.admintools.lessonslist.data.cache.ChosenLessonIdCache
import ru.easycode.words504.admintools.reviewLessonContent.mappers.ExerciseItemCloudToQuestionString
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonCacheToContentUiMapper
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonCloudToContentUiMapper
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonExerciseToLessonPreviewMapper
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonQuoteCloudToPreviewMapper
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonTextCloudToPreviewMapper
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonWordCloudToUi
import ru.easycode.words504.admintools.reviewLessonContent.mappers.LessonWordsCloudToWordPreviewMapper
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentCommunication
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentViewModel
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class ReviewLessonContentModule(private val coreModule: CoreModule) :
    Module<ReviewLessonContentViewModel.Base> {
    override fun viewModel(): ReviewLessonContentViewModel.Base {
        val adminScope = coreModule.provideAdminScope()
        val sentenceCloudMapper = SentenceCloud.Mapper.Ui(WordCloud.Mapper.Ui)
        return ReviewLessonContentViewModel.Base(
            repository = LessonsListRepositoryBase(
                lessonsDao = adminScope.provideDatabase().lessonsDao(),
                chosenLessonIdCache = ChosenLessonIdCache.Base(adminScope.provideObjectStorage()),
                Serialization.Base()
            ),
            mapper = LessonCacheToContentUiMapper(
                LessonCloudToContentUiMapper(
                    quoteMapper = LessonQuoteCloudToPreviewMapper(sentenceCloudMapper),
                    wordsMapper = LessonWordsCloudToWordPreviewMapper.Base(LessonWordCloudToUi()),
                    textMapper = LessonTextCloudToPreviewMapper(sentenceCloudMapper),
                    exerciseMapper = LessonExerciseToLessonPreviewMapper(
                        ExerciseItemCloudToQuestionString(sentenceCloudMapper)
                    ),
                    manageResources = coreModule
                )
            ),
            communication = ReviewLessonContentCommunication.Base(),
            navigation = adminScope.provideNavigation(),
            dispatchersList = coreModule.provideDispatchers()
        )
    }
}
