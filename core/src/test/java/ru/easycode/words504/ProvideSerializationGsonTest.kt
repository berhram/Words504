package ru.easycode.words504

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runners.Parameterized.Parameter
import ru.easycode.words504.data.ProvideSerializationGson


class ProvideSerializationGsonTest() {

    data class FakeSerializedObject(val id: String)

    private val gson = Gson()
    private val provideSerializationGson = ProvideSerializationGson.Base(gson)

    @Test
    fun readEmptyString() {
        val fakeData = FakeSerializedObject("")
        val actual = provideSerializationGson.toJson(fakeData)
        val expected = "{\"id\":\"\"}"
        assertEquals(expected, actual)
    }

    @Test
    fun readEmptyJson() {
        val actual =
            provideSerializationGson.fromJson("{\"id\":\"\"}", FakeSerializedObject::class.java)
        val expected = FakeSerializedObject("")
        assertEquals(expected, actual)
    }

    @Test
    fun readRightJson() {
        val actual =
            provideSerializationGson.fromJson("{\"id\":\"777\"}", FakeSerializedObject::class.java)
        val expected = "{\"id\":\"777\"}"
        assertEquals(actual, expected)
    }


}