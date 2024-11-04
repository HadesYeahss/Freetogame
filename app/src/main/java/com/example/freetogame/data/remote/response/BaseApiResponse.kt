package com.example.freetogame.data.remote.response


import kotlinx.coroutines.flow.FlowCollector

/**
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
abstract class BaseApiResponse {
    suspend inline fun <T> safeApiCall(
        crossinline body: suspend () -> T
    ): ResponseResult<T> {
        return try {
            val result  = body()
            ResponseResult.Success(result)
        } catch (e: Exception) {
            ResponseResult.Failure(e)
        }
    }

    protected suspend inline fun <T> emitSafeApiCall(
        flow: FlowCollector<ResponseResult<T>>,
        crossinline body: suspend () -> T
    ) {
        flow.emit(safeApiCall(body))
    }
}