package ru.easycode.words504.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.data.cache.storage.Storage

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
    fun `read string when Storage is empty`() {
        val actual = storage.read(fakeKey, fakeDefaultString)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `save string to Storage`() {
        storage.save(fakeKey, fakeDefaultString)

        val actual = fakeSimpleStorage.read(fakeKey, newFakeDefault)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `read object when Storage is empty`() {
        val actual = storage.read(fakeKey, fakeDefaultString)
        val expected = fakeDefaultString
        assertEquals(expected, actual)
    }

    @Test
    fun `save object to Storage`() {
        storage.save(fakeKey, 1)

        val actual = fakeObjectStorage.read(fakeKey, 0)
        val expected = 1
        assertEquals(expected, actual)
    }
}
