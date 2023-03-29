package ru.easycode.words504.presentation.custom

interface InputState {

    fun <T : Any> map(mapper: Mapper<T>): T

    class Base(
        private val sequence: CharSequence,
        private val previousLength: Int,
        private val previousSymbol: Char
    ) : InputState {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            val currentLength = sequence.length
            val currentSymbol = if (sequence.isNotEmpty()) sequence.last() else EMPTY
            val beforePreviousSymbol =
                if (currentLength > 2) sequence[sequence.length - 3] else EMPTY
            return mapper.map(
                currentLength,
                previousSymbol,
                currentSymbol,
                previousLength,
                beforePreviousSymbol
            )
        }
    }
    companion object {
        private const val EMPTY = ' '
    }
}
