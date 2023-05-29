package ru.easycode.words504

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.easycode.words504.data.cache.DataBase

class RoomDatabaseWrapper<T : RoomDatabase>(
    context: Context,
    clazz: Class<T>,
    name: String
) : DataBase<T> {

    private val roomBuilder = Room.databaseBuilder(context, clazz, name)
        .fallbackToDestructiveMigration()

    override fun create(): T = roomBuilder.build()

    override fun <K : Any> create(typeConverter: K): T =
        roomBuilder.addTypeConverter(typeConverter).build()
}
