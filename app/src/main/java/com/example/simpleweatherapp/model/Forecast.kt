package com.example.simpleweatherapp.model


data class Forecast(
    val main: Main,
    val weather: List<Weather>
)