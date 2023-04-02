package ru.easycode.words504.presentation.custom

import ru.easycode.words504.domain.Mapper

class HandleIndexes(
    private val letter: Mapper<Char, Boolean>,
    private val delimiter: Mapper<Char, Boolean>
) : Mapper<String, String> {
    override fun map(source: String): String {
        var output = ""
        source.forEachIndexed { index, char ->
            val previousSymbol = if (index > 0) source[index - 1] else ' '
            val charOutput =
                if (delimiter.map(previousSymbol) && letter.map(char)) index else '_'
            output += charOutput
        }
        return output
    }
}
