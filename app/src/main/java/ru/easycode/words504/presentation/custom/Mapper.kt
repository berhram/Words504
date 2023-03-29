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
        ): Boolean = currentLength > previousLength && currentLength > 1 &&
                delimit(currentSymbol) && previousSymbol != ' '
    }

    class Remove : Abstract() {

        override fun map(
            currentLength: Int,
            currentSymbol: Char,
            previousSymbol: Char,
            previousLength: Int,
            beforePreviousSymbol: Char
        ): Boolean =
            delimit(previousSymbol) && currentLength < previousLength && previousSymbol != beforePreviousSymbol
    }

}
