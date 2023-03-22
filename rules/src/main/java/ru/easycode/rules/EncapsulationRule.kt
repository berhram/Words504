package ru.easycode.rules

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.startOffset
import org.jetbrains.kotlin.psi.psiUtil.visibilityModifierTypeOrDefault

class EncapsulationRule : Rule("encapsulation-rule") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        node.psi?.let { element ->
            val visibility = when (element) {
                is KtParameter -> element.visibilityModifierTypeOrDefault()
                is KtProperty -> element.visibilityModifierTypeOrDefault()
                else -> return
            }
            val name = when (element) {
                is KtParameter -> element.name
                is KtProperty -> element.name
                else -> return
            }
            if (visibility == KtTokens.PUBLIC_KEYWORD || visibility == KtTokens.INTERNAL_KEYWORD) {
                emit(
                    element.startOffset,
                    "Property $name must be private or protected",
                    false
                )
            }
        }
    }
}


