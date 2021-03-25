package com.example.simpleweatherapp.model.api

import androidx.lifecycle.MutableLiveData
import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val UNITS = "metric"
        const val API_KEY = "69135546828ada76b54fe3f6e48c5498"
    }

    var forecast = MutableLiveData<Forecast>()

    private var retrofit: Retrofit
    private var apiService: APIService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }

    fun getDataFromAPI(city: String, callback: RepositoryCallback<Forecast>) {
        apiService.getCurrentWeather(city, UNITS, API_KEY).enqueue(object : Callback<Forecast> {
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                callback.onError("error happened")
            }

            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                    forecast.value = response.body()
                }
            }

        })
    }

}