package ru.easycode.words504.admintools.presentation.input

import ru.easycode.words504.admintools.presentation.WordUi
import ru.easycode.words504.data.Read
import ru.easycode.words504.data.Save

interface WordsUiContainer : Save<List<WordUi>>, Read<List<WordUi>> {
    fun add()
}
