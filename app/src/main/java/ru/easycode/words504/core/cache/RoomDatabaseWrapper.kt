package ru.easycode.words504.core.cache

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.easycode.words504.data.cache.DataBase
import ru.easycode.words504.data.cache.GenericConverter

class RoomDatabaseWrapper<T : RoomDatabase>(
    context: Context,
    clazz: Class<T>,
    name: String
) : DataBase<T> {

    private val roomBuilder = Room.databaseBuilder(context, clazz, name)
        .fallbackToDestructiveMigration()

    override fun create(): T = roomBuilder.build()

    override fun <S : Any, V : Any, C : GenericConverter<S, V>> create(typeConverter: C): T =
        roomBuilder.addTypeConverter(typeConverter).build()
}
