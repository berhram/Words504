package ru.easycode.words504.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class StorageTest {
    private lateinit var fakeKey: String
    private lateinit var fakeDefaultString: String
    private lateinit var fakeSimpleStorage: FakeSimpleStorage
    private lateinit var fakeObjectStorage: FakeObjectStorage
    private lateinit var storage: Storage

    @Before
    fun setUp() {
        fakeKey = "some key"
        fakeDefaultString = "fake string"
        fakeSimpleStorage = FakeSimpleStorage()
        fakeObjectStorage = FakeObjectStorage()
        storage = Storage.Base(fakeSimpleStorage, fakeObjectStorage)
    }


    @Test
    fun `test read from SimpleStorage empty`() {
        val actual = storage.read(fakeKey, fakeDefaultString)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `test save and read from SimpleStorage`() {
        val string = "some string"

        storage.save(fakeKey, string)

        val actual = storage.read(fakeKey, fakeDefaultString)
        val expected = string
        assertEquals(expected, actual)
    }

    @Test
    fun `test read from ObjectStorage empty`() {
        val fakeDefaultClass = FakeClass(1, "1")

        val actual = storage.read(fakeKey, fakeDefaultClass)
        val expected = fakeDefaultClass
        assertEquals(expected, actual)
    }

    @Test
    fun `test save and read from ObjectStorage`() {
        val fakeDefaultClass = FakeClass(1, "1")
        val fakeClass = FakeClass(2, "2")

        storage.save(fakeKey, fakeClass)

        val actual = storage.read(fakeKey, fakeDefaultClass)
        val expected = fakeClass
        assertEquals(expected, actual)
    }


    private class FakeSimpleStorage : SimpleStorage {
        private var string = ""

        override fun read(key: String, default: String): String =
            string.ifEmpty { default }

        override fun save(key: String, value: String) {
            string = value
        }
    }

    private class FakeObjectStorage : ObjectStorage {
        var fakeClass = FakeClass(0, "")

        override fun save(key: String, obj: Any) {
            fakeClass = obj as FakeClass
        }

        override fun <T : Any> read(key: String, default: T): T =
            if (Objects.equals(fakeClass, FakeClass(0, "")))
                default
            else fakeClass as T

    }

    private data class FakeClass(private var id: Int, private var data: String)
}