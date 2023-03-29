package ru.easycode.words504.presentation.custom

import ru.easycode.words504.core.presentation.custom.Show

interface HelperLineState : Show<List<InputCondition>, String> {

    class Base(private var indexesOutput: String) : HelperLineState {
        override fun show(source: List<InputCondition>): String {
            source.forEach { input ->
                if (input.condition()) {
                    indexesOutput = input.show(indexesOutput)
                }
            }
            return indexesOutput
        }
    }
}
