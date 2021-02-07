package com.domain.task.core.data.remote

import com.domain.task.core.connection.ConnectionManager
import com.domain.task.core.data.Result
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource constructor(private val connectionManager: ConnectionManager) {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            if (!connectionManager.isNetworkAvailable()) {
                return error(Result.ErrorCode.NOT_CONNECTED)
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(Result.ErrorCode.SERVER_UNEXPECTED_ERROR)
        } catch (e: IOException) {
            e.printStackTrace()
            return error(Result.ErrorCode.NO_INTERNET)
        } catch (e: Exception) {
            e.printStackTrace()
            return error(Result.ErrorCode.APP_UNEXPECTED_ERROR)
        }
    }

    private fun <T> error(code: Result.ErrorCode): Result<T> {
        return Result.error(code)
    }

}

