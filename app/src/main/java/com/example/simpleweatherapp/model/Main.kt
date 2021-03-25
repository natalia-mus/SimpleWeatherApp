package com.example.simpleweatherapp.model

import com.google.gson.annotations.SerializedName

data class Main(
    val humidity: Int,
    val pressure: Int,
    @SerializedName("temp")
    val temperature: Double
)