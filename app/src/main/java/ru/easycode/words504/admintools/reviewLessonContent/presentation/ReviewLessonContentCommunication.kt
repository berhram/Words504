package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.presentation.Communication

interface ReviewLessonContentCommunication : Communication.Mutable<ReviewLessonContentState> {
    class Base :
        Communication.Abstract<ReviewLessonContentState>(),
        ReviewLessonContentCommunication
}
