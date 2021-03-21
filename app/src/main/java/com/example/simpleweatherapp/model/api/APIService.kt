package com.example.simpleweatherapp.model.api

import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    /*@GET("weather?q=Barcelona&units=metric&appid=69135546828ada76b54fe3f6e48c5498")
    fun getCurrentWeather(): Call<Forecast>*/

    /*@GET("weather?q={city}")
    fun getCurrentWeather(@Path("city") city: String): Call<Forecast>*/

    @GET("weather")
    fun getCurrentWeather(@Query("q") city: String, @Query("units") units: String, @Query("appid") apiKey: String): Call<Forecast>

    /*@GET("weather?q={city}&units=metric&appid=69135546828ada76b54fe3f6e48c5498")
    fun getCurrentWeather(@Query("city") city: String): Call<Forecast>*/

}