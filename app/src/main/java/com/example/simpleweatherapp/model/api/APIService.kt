package com.example.simpleweatherapp.model.api

import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("weather?q=Barcelona&units=metric&appid=69135546828ada76b54fe3f6e48c5498")
    fun getCurrentWeather(): Call<Forecast>
}