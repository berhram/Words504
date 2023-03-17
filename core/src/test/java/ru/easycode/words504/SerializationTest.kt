package ru.easycode.words504

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import ru.easycode.words504.data.Serialization


class SerializationTest() {
    private data class FakeSerializedObject(val id: String)

    private val gson = Gson()
    private val serialization = Serialization.Base(gson)

    @Test
    fun `test deserialize object`() {
        val actual =
            serialization.fromJson("{\"id\":\"777\"}", FakeSerializedObject::class.java)
        val expected = FakeSerializedObject("777")
        assertEquals(expected, actual)
    }

    @Test
    fun `test serialize object`() {
        val actual =
            serialization.toJson(FakeSerializedObject("777"))
        val expected = "{\"id\":\"777\"}"
        assertEquals(expected, actual)
    }
}