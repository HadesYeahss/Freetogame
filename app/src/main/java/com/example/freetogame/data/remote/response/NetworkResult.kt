package com.example.freetogame.data.remote.response

/**
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
sealed class ResponseResult<out H> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Failure(val error: Throwable) : ResponseResult<Nothing>()
}

