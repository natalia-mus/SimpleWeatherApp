package com.example.simpleweatherapp.model.api

interface RepositoryCallback<T> {

    fun onSuccess(data: T?)
    fun onError(error: String?)
}