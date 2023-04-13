package ru.easycode.words504.admintools.presentation.input

import ru.easycode.words504.domain.Mapper

class HandleIndexes(
    private val letter: Mapper<Char, Boolean>,
    private val apostrophe: Mapper<Char, Boolean>
) : Mapper<String, String> {
    override fun map(source: String): String {
        var output = ""
        source.forEachIndexed { index, char ->
            val previousSymbol = if (index > 0) source[index - 1] else SPACE_CHAR
            val showIndex = with(letter) {
                map(previousSymbol).not() && map(char) && apostrophe.map(previousSymbol).not()
            }
            output += if (showIndex) index else FILLER_CHAR
        }
        return output
    }

    companion object {
        private const val SPACE_CHAR = ' '
        private const val FILLER_CHAR = '_'
    }
}
