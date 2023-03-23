package ru.easycode.words504.languages

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.languages.data.cloud.LanguageCloud

class LanguageCloudModelTest {

    @Test
    fun `test language is english`() {
        val languageCloud = LanguageCloud.Base("EN", "English")
        assertEquals(true, languageCloud.isLanguageEnglish())
    }

    @Test
    fun `test language is british english`() {
        val languageCloud = LanguageCloud.Base("EN-GB", "English")
        assertEquals(true, languageCloud.isLanguageEnglish())
    }

    @Test
    fun `test language is not english`() {
        val languageCloud = LanguageCloud.Base("DE", "German")
        assertEquals(false, languageCloud.isLanguageEnglish())
    }
}
