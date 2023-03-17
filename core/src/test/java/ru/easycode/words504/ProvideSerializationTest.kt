package ru.easycode.words504

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import ru.easycode.words504.data.ProvideSerialization


class ProvideSerializationTest() {

    data class FakeSerializedObject(val id: String)

    private val gson = Gson()
    private val provideSerialization = ProvideSerialization.Base(gson)

    @Test
    fun readEmptyString() {
        val actual = provideSerialization.toJson(FakeSerializedObject(""))
        val expected = "{\"id\":\"\"}"
        assertEquals(expected, actual)
    }

    @Test
    fun readEmptyObject() {
        val actual =
            provideSerialization.fromJson("{\"id\":\"\"}", FakeSerializedObject::class.java)
        val expected = FakeSerializedObject("")
        assertEquals(expected, actual)
    }

    @Test
    fun readRightObject() {
        val actual =
            provideSerialization.fromJson("{\"id\":\"777\"}", FakeSerializedObject::class.java)
        val expected = "{\"id\":\"777\"}"
        assertNotEquals(actual, expected)
    }
    @Test
    fun readRightString() {
        val actual =
            provideSerialization.toJson(FakeSerializedObject("id:777"))
        val expected = "{\"id\":\"777\"}"
        assertNotEquals(actual, expected)
    }
}