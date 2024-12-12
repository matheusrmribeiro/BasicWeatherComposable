package com.example.basicweathercomposable.core.base

sealed class ViewState<out T> {
    data class ViewStateSuccess<out T>(val data: T) : ViewState<T>()
    data class ViewStateLoading(val message: String? = null) : ViewState<Nothing>()
    data class ViewStateError(val errorMessage: String) : ViewState<Nothing>()
}