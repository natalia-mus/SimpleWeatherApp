package com.example.simpleweatherapp.model.api

import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    // https://api.openweathermap.org/data/2.5/weather?q=Krakow&units=metric&appid=69135546828ada76b54fe3f6e48c5498

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val UNITS = "metric"
    private const val API_KEY = "69135546828ada76b54fe3f6e48c5498"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var apiService: APIService = retrofit.create(APIService::class.java)

    fun getDataFromAPI(city: String, callback: RepositoryCallback<Forecast>) {
        apiService.getCurrentWeather(city, UNITS, API_KEY).enqueue(object : Callback<Forecast> {
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                callback.onError("error happened")
            }

            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                }
            }

        })
    }

}
