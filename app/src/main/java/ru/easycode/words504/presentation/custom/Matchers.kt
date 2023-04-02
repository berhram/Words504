package ru.easycode.words504.presentation.custom

import ru.easycode.words504.domain.Mapper

class LetterMatch : Mapper<Char, Boolean> {
    override fun map(source: Char): Boolean = Character.isLetter(source)
}

class DelimiterMatch : Mapper<Char, Boolean> {
    override fun map(source: Char): Boolean = Character.isSpaceChar(source)
}
