package ru.easycode.rules

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.startOffset
import org.jetbrains.kotlin.psi.psiUtil.visibilityModifierTypeOrDefault

class EncapsulationRule : Rule("encapsulation-rule") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.psi is KtClass) {
            val properties = (node.psi as KtClass).getProperties()
            val constructor = (node.psi as KtClass).primaryConstructor
            properties.forEach {
                if (it.visibilityModifierTypeOrDefault().isViolating()) emit(
                    it.startOffset, "Property ${it.name} must be private or protected", false
                )
            }
            constructor?.valueParameters?.filter { it.hasValOrVar() }?.forEach { param ->
                if (param.visibilityModifierTypeOrDefault().isViolating()) emit(
                    param.startOffset,
                    "Constructor argument ${param.name} must be private or protected",
                    false
                )
            }
        }
    }

    private fun KtModifierKeywordToken.isViolating() =
        this == KtTokens.PUBLIC_KEYWORD || this == KtTokens.INTERNAL_KEYWORD
}


