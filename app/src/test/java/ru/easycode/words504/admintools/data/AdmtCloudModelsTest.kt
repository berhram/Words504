package ru.easycode.words504.admintools.data

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.admintools.data.cloud.LessonCloud
import ru.easycode.words504.data.cache.serialization.Serialization

class AdmtCloudModelsTest {

    @Test
    fun `test lesson cloud`() {
        val jsonString = javaClass.classLoader?.getResource(PATH_FILE)?.readText() ?: ""

        val serialization = Serialization.Base()
        val lessonCloud = serialization.fromJson(jsonString, LessonCloud.Base::class.java)
        assertEquals(LessonCloud.Base::class.java, lessonCloud::class.java)
    }

    companion object {
        private const val PATH_FILE = "raw/lesson.json"
    }
}
