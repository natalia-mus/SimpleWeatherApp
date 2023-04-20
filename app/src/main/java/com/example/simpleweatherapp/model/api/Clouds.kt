package com.example.simpleweatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val cloudiness: Int
)
