package ru.easycode.words504.data.cloud

import retrofit2.Call
import ru.easycode.words504.domain.HandleError

abstract class AbstractCloudDataSource(
    private val errorHandler: HandleError<Exception, Throwable>
) {

    protected suspend fun <T : Any> handle(
        block: suspend () -> Call<T>
    ): T = try {
        val response = block.invoke().execute()
        response.body()!!
    } catch (e: Exception) {
        throw errorHandler.handle(e)
    }
}
