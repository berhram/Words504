package ru.easycode.words504.presentation.custom

import ru.easycode.words504.domain.Mapper

class HandleIndexes : Mapper<String, String> {
    override fun map(source: String): String {
        var output = ""
        source.forEachIndexed { index, char ->
            val previousSymbol = if (index > 0) source[index - 1] else ' '
            val charOutput =
                if (previousSymbol == ' ' && Character.isLetter(char)) index else '_'
            output += charOutput
        }
        return output
    }
}
