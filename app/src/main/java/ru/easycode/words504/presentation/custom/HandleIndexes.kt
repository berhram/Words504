package ru.easycode.words504.presentation.custom

interface HandleIndexes {

    fun handle(input: String): String

    class Base : HandleIndexes {
        override fun handle(input: String): String {
            var output = ""
            if (input.isEmpty()) output = ""
            input.forEachIndexed { index, char ->
                val previousSymbol = if (index > 0) input[index - 1] else ' '
                val charOutput =
                    if (previousSymbol == ' ' && Character.isLetter(char)) index else '_'
                output += charOutput
            }
            return output
        }
    }
}
