package com.example.basedatos2.utils

sealed class MResource<out T> {

    data class Success<out T>(val data: T?) : MResource<T>()
    data class Failure<out T>(val exception: Exception) : MResource<T>()

}
