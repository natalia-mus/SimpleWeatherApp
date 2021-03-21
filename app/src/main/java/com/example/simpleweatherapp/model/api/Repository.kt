package com.example.simpleweatherapp.model.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class Repository {

    companion object {
        const val UNITS = "metric"
        const val API_KEY = "69135546828ada76b54fe3f6e48c5498"
    }

    var instance: APIService? = null

    var forecast = MutableLiveData<Forecast>()

    /*val CITY = "Austin"
    val UNITS = "metric"
    val API_KEY = "69135546828ada76b54fe3f6e48c5498"*/

    private var retrofit: Retrofit
    private var apiService: APIService

    init {
        retrofit = Retrofit.Builder()
            //.baseUrl("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY")
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)


    }

    fun getDataFromAPI(city: String, callback: RepositoryCallback<Forecast>) {
        //CITY = "$CITY&units=metric&appid=69135546828ada76b54fe3f6e48c5498"
        //Log.e("city", CITY)
        apiService.getCurrentWeather(city, UNITS, API_KEY).enqueue(object : Callback<Forecast> {
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.e("ERROR", "ERROR")
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