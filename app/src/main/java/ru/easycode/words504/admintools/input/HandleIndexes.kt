package ru.easycode.words504.admintools.input

import ru.easycode.words504.domain.Mapper

class HandleIndexes(
    private val letter: Mapper<Char, Boolean>,
    private val apostrophe: Mapper<Char, Boolean>
) : Mapper<String, String> {
    override fun map(source: String): String {
        var output = ""
        source.forEachIndexed { index, char ->
            val previousSymbol = if (index > 0) source[index - 1] else SPACE_CHAR
            val showUnderscore = with(letter) {
                map(previousSymbol) || map(char).not() || apostrophe.map(previousSymbol)
            }
            output += if (showUnderscore) UNDERSCORE_CHAR else index
        }
        return output
    }

    companion object {
        private const val SPACE_CHAR = ' '
        private const val UNDERSCORE_CHAR = '_'
    }
}
