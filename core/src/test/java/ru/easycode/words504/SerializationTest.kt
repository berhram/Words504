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
    fun readEmptyString() {
        val actual = serialization.toJson(FakeSerializedObject(""))
        val expected = "{\"id\":\"\"}"
        assertEquals(expected, actual)
    }

    @Test
    fun readEmptyObject() {
        val actual =
            serialization.fromJson("{\"id\":\"\"}", FakeSerializedObject::class.java)
        val expected = FakeSerializedObject("")
        assertEquals(expected, actual)
    }

    @Test
    fun `test deserialize object`() {
        val actual =
            serialization.fromJson("{\"id\":\"777\"}", FakeSerializedObject::class.java)
        val expected = "{\"id\":\"777\"}"
        assertNotEquals(actual, expected)
    }

    @Test
    fun `test serialize object`() {
        val actual =
            serialization.toJson(FakeSerializedObject("id:777"))
        val expected = "{\"id\":\"777\"}"
        assertNotEquals(actual, expected)
    }
}