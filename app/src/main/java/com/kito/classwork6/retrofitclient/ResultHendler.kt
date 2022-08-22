package com.kito.classwork6.retrofitclient

sealed class ResultHendler<T> {
    data class Success<T>(val data: T) : ResultHendler<T>()
    data class Error<T>(val errorMSg: String) : ResultHendler<T>()
}
