package ru.easycode.words504.presentation.custom

interface Mapper<T : Any> {
    fun map(
        currentLength: Int,
        currentSymbol: Char,
        previousSymbol: Char,
        previousLength: Int,
        beforePreviousSymbol: Char
    ): T

    abstract class Abstract : Mapper<Boolean>, Delimit.Abstract()

    class Add : Abstract() {
        override fun map(
            currentLength: Int,
            currentSymbol: Char,
            previousSymbol: Char,
            previousLength: Int,
            beforePreviousSymbol: Char
        ): Boolean {
            val isTyping = currentLength > previousLength
            val delimiterPassed = delimit(currentSymbol) && previousSymbol != ' '
            return isTyping && delimiterPassed && currentLength > 1
        }
    }

    class Remove : Abstract() {

        override fun map(
            currentLength: Int,
            currentSymbol: Char,
            previousSymbol: Char,
            previousLength: Int,
            beforePreviousSymbol: Char
        ): Boolean {
            val delimiterReached = delimit(previousSymbol) && previousSymbol != beforePreviousSymbol
            val isRemoving = currentLength < previousLength
            return delimiterReached && isRemoving
        }
    }
}
