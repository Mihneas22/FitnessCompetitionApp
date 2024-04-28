package com.example.fitnesscompetitionapp.ui.mainapp.domain.util

sealed class Resource<out T> {
    data class Success<out T>(val result: T): Resource<T>()
    data class Failure(val ex: Exception): Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}