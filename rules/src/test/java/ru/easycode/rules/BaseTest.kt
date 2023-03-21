package ru.easycode.rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.RuleProvider
import com.pinterest.ktlint.test.lint
import org.intellij.lang.annotations.Language
import org.junit.Assert.assertEquals

abstract class BaseTest(rule: Rule) {

    private val provider = setOf(RuleProvider { rule })

    fun assertNoLintErrors(@Language("kotlin") code: String) {
        assert(
            provider.lint(code).isEmpty()
        ) { "There should't be an lint errors" }
    }

    fun assertLintErrors(
        @Language("kotlin") code: String,
        vararg errors: Pair<Int, Int>
    ) {
        assertEquals(errors.toList(), provider.lint(code).map { it.line to it.col })
    }
//    fun assertFormat(
//        @Language("kotlin") code: String,
//        @Language("kotlin") expectedCode: String
//    ) {
//        assertEquals(expectedCode, provider.format(code))
//    }
}