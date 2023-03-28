package ru.easycode.rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.test.lint
import org.intellij.lang.annotations.Language
import org.junit.Assert.assertTrue

abstract class BaseTest(rule: Rule) {

    private val provider = setOf(RuleProvider { rule })

    protected fun assertNoLintErrors(@Language("kotlin") code: String) {
        assertTrue(provider.lint(code.trimIndent()).isEmpty())
    }

    protected fun assertLintErrors(@Language("kotlin") code: String) {
        assertTrue(provider.lint(code.trimIndent()).isNotEmpty())
    }
}
