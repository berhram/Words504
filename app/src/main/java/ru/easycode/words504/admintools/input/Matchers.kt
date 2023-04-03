package ru.easycode.words504.admintools.input

import ru.easycode.words504.domain.Mapper

class LetterMatch : Mapper<Char, Boolean> {
    override fun map(source: Char): Boolean = Character.isLetter(source)
}

class ApostropheMatch : Mapper<Char, Boolean> {
    override fun map(source: Char): Boolean = source == APOSTROPHE_CHAR

    companion object {
        private const val APOSTROPHE_CHAR = '\''
    }
}
