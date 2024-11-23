package com.aliza.alizaparse.utils.net

sealed class ParseRequest<out T>(val data: T? = null, val error: String? = null) {
    class Idle<T> : ParseRequest<T>()
    class Loading<T> : ParseRequest<T>()
    class Success<T>(data: T) : ParseRequest<T>(data)
    class Error<T>(error: String) : ParseRequest<T>(error = error)
}