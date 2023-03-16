package ru.easycode.words504

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.data.ProvideSerializationGson


class ProvideSerializationGsonTest( )  {

    data class FakeSerializedObject(val id: String)
    private val gson = Gson()
    private val provideSerializationGson = ProvideSerializationGson.Base(gson)

    @Test
    fun readEmptyString(){
        var fakeData = FakeSerializedObject("")

        var tojson = provideSerializationGson.toJson(fakeData)
        val actual = tojson
        val expected ="{\"id\":\"\"}"
        assertEquals(expected,actual)
    }

    @Test
    fun readEmptyJson(){
        var fakeData = FakeSerializedObject("")
        val actual = provideSerializationGson.fromJson("{\"id\":\"\"}",FakeSerializedObject::class.java)
        val expected = FakeSerializedObject("")
        assertEquals(expected,actual)

    }
}