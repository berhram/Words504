package ru.easycode.words504.admintools.presentation

import ru.easycode.words504.data.Read
import ru.easycode.words504.data.Save

interface SaveAndReadWordUi : Save<WordUi>, Read<WordUi>