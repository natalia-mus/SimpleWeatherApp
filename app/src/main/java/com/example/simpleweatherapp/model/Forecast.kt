package com.example.simpleweatherapp.model


data class Forecast(
    //@SerializedName("main")
    val main: Main,
    //@SerializedName("weather")
    val weather: List<Weather>
)