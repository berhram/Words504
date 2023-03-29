package ru.easycode.words504.presentation.custom

import ru.easycode.words504.core.presentation.custom.Show

interface InputCondition : Show<String, String> {

    fun condition(): Boolean

    abstract class Abstract(
        sequence: CharSequence,
        private val inputState: InputState,
        private val delimiter: Mapper<Boolean>
    ) : InputCondition {
        override fun condition(): Boolean = inputState.map(delimiter)
        protected val currentIndex = sequence.lastIndex
    }

    class Add(sequence: CharSequence, inputState: InputState, mapper: Mapper<Boolean>) :
        Abstract(sequence, inputState, mapper) {
        override fun show(source: String): String = "$source...$currentIndex"
    }

    class Remove(sequence: CharSequence, inputState: InputState, mapper: Mapper<Boolean>) :
        Abstract(sequence, inputState, mapper) {
        override fun show(source: String): String {
            val suffixToRemove = currentIndex.toString().length + 3
            return source.dropLast(suffixToRemove)
        }
    }

    class Empty(private val sequence: CharSequence) : InputCondition {
        override fun show(source: String): String = source
        override fun condition(): Boolean = sequence.isEmpty()
    }

    class Start(private val sequence: CharSequence) : InputCondition {
        override fun show(source: String): String = "0"
        override fun condition(): Boolean = sequence.length == 1
    }
}
