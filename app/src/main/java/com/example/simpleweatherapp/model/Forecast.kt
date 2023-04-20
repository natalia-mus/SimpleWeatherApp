package com.example.simpleweatherapp.model

import com.example.simpleweatherapp.model.api.Clouds
import com.google.gson.annotations.SerializedName

data class Forecast(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val clouds: Clouds,
    val sys: Sys,
    @SerializedName("timezone")
    val timeZone: Long,
)