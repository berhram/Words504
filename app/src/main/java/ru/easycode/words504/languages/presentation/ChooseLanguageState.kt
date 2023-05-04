package ru.easycode.words504.languages.presentation

import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.isVisible
import ru.easycode.words504.R
import ru.easycode.words504.domain.Mapper

interface ChooseLanguageState {
    fun map(adapter: Mapper.Unit<List<LanguageUi>>, screen: LinearLayout)

    abstract class Abstract(
        private val savingEnabled: Boolean,
        private val screenVisible: Boolean
    ) : ChooseLanguageState {
        override fun map(
            adapter: Mapper.Unit<List<LanguageUi>>,
            screen: LinearLayout
        ) {
            val saveButton = screen.findViewById<Button>(R.id.saveButton)
            screen.isVisible = screenVisible
            saveButton.isEnabled = savingEnabled
        }
    }

    data class Initial(private val languages: List<LanguageUi>) : Abstract(false, true) {

        override fun map(
            adapter: Mapper.Unit<List<LanguageUi>>,
            screen: LinearLayout
        ) {
            super.map(adapter, screen)
            adapter.map(languages)
        }
    }

    data class Chosen(private val languages: List<LanguageUi>) : Abstract(true, true) {

        override fun map(
            adapter: Mapper.Unit<List<LanguageUi>>,
            screen: LinearLayout
        ) {
            super.map(adapter, screen)
            adapter.map(languages)
        }
    }

    object Saved : Abstract(true, false)
}
