package com.example.basicweathercomposable.core.network

sealed class ResponseWrapper<out T> {
    data class ResponseSuccess<out T>(val data: T) : ResponseWrapper<T>()
    data class ResponseError(val errorMessage: String) : ResponseWrapper<Nothing>()
}