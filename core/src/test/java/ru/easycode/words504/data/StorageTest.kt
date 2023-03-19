package ru.easycode.words504.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest

class StorageTest : BaseTest() {
    private lateinit var fakeKey: String
    private lateinit var fakeDefaultString: String
    private lateinit var newFakeDefault: String
    private lateinit var fakeSimpleStorage: FakeSimpleStorage
    private lateinit var fakeObjectStorage: FakeObjectStorage
    private lateinit var storage: Storage

    @Before
    fun setUp() {
        fakeKey = "some key"
        fakeDefaultString = "fake string"
        newFakeDefault = "new default fake string"
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
        storage.save(fakeKey, fakeDefaultString)

        val actual = storage.read(fakeKey, newFakeDefault)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `test read from ObjectStorage empty`() {
        val actual = storage.read(fakeKey, fakeDefaultString)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `test save and read from ObjectStorage`() {
        storage.save(fakeKey, fakeDefaultString)

        val actual = storage.read(fakeKey, newFakeDefault)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }
}
