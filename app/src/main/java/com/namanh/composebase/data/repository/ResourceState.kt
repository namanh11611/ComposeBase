package com.namanh.composebase.data.repository

sealed class ResourceState<out T> {
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val message: String): ResourceState<T>()
    object Loading: ResourceState<Nothing>()

    fun getCurrentData(): T? {
        return when (this) {
            is Success -> this.data
            is Error, Loading -> null
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$]"
            is Error -> "Error[message=$message]"
            is Loading -> "Loading"
        }
    }
}
