package ru.easycode.rules

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EncapsulationRuleTest : BaseTest(EncapsulationRule()) {

    @Test
    fun `encapsulation rule passed`() {
        val textList = listOf(
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "class Repository(private val dataSource: DataSource) {\n" +
                    "\n" +
                    "    private var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(private val dataSource: DataSource) {\n" +
                    "\n" +
                    "    protected var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(protected val dataSource: DataSource) {\n" +
                    "\n" +
                    "    private var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(protected val dataSource: DataSource) {\n" +
                    "\n" +
                    "    protected var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(protected val dataSource: DataSource) {\n" +
                    "\n" +
                    "    protected abstract val page: Int\n" +
                    "}",
        )
        textList.forEach { text ->

        }
        //todo rewrite to """ strings
    }

    @Test
    fun `encapsulation rule not passed`() {
        val rule: GoodCodeRule = GoodCodeRule.Encapsulation()
        val textList = listOf(
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "class Repository(val dataSource: DataSource) {\n" +
                    "\n" +
                    "    var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "class Repository(private val dataSource: DataSource) {\n" +
                    "\n" +
                    "    var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "class Repository(val dataSource: DataSource) {\n" +
                    "\n" +
                    "   private var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(protected val dataSource: DataSource) {\n" +
                    "\n" +
                    "    var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(val dataSource: DataSource) {\n" +
                    "\n" +
                    "   protected var page: Int = 0\n" +
                    "}",
            "package com.github.johnnysc.practicetdd\n" +
                    "\n" +
                    "abstract class Repository(val dataSource: DataSource) {\n" +
                    "\n" +
                    "   protected abstract val page: Int\n" +
                    "}",
        )
        textList.forEach { text ->
            val actual = rule.isValid(text = text)
            val expected = false
            assertEquals(expected, actual)
        }
    }
}