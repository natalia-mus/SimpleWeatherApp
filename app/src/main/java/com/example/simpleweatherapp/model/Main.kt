package com.example.simpleweatherapp.model

import com.google.gson.annotations.SerializedName

data class Main(
    val humidity: Int,
    val pressure: Int,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)