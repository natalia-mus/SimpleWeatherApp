package com.example.simpleweatherapp.model.api

import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<Forecast>

}