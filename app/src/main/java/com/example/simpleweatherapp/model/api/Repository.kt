package com.example.simpleweatherapp.model.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simpleweatherapp.model.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    var instance: APIService? = null

    val CITY = "Katowice"
    val API_KEY = "69135546828ada76b54fe3f6e48c5498"

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: APIService

    init {
        retrofit = Retrofit.Builder()
            //.baseUrl("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY")
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)


    }

    //fun initialize(): APIService = apiService

    /*fun getRepositoryInstance(): APIService {
        if (instance == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(APIService::class.java)

            instance = apiService
        }
        return instance as APIService
    }*/

    //private var forecast: Forecast? = null      // wcześniej 'forecast' to było 'data'
    //var fore: String = "aaa"


    lateinit var fore: MutableLiveData<String>
    //lateinit var forecast: MutableLiveData<Forecast>


    var forecast = MutableLiveData<Forecast>()
    //var forecast: LiveData<Forecast> = _forecast

    /*fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = apiService.getCurrentWeather().awaitResponse()
            if(response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data = response.body()!!
                    Log.e("data", data.toString())
                    fore.value = response.body()!!.main.temperature.toString()
                    Log.e("fore", fore.value)
                }
            }
        }
    }*/







    /*fun getCurrentWeather(callback: RepositoryCallback<Forecast>) {
        apiService.getCurrentWeather(callback)
    }*/






    /*fun getDataFromAPI() {
        apiService.getCurrentWeather(object: OperationCallback<Forecast> {
            override fun onError(error: String?) {
                Log.e("ERROR", "ERROR")
            }

            override fun onSuccess(data: Forecast) {
                forecast.value = data
            }
        })
    }*/

    /*fun getDataFromAPI() {
        apiService.getCurrentWeather(object: OperationCallback<Forecast> {
            override fun onError(error: String?) {
                Log.e("ERROR", "ERROR")
            }

            override fun onSuccess(data: Forecast?) {
                forecast.value = data
            }
        })
    }*/

    fun getDataFromAPI(callback: RepositoryCallback<Forecast>) {
        apiService.getCurrentWeather().enqueue(object: Callback<Forecast> {
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.e("ERROR", "ERROR")
                callback.onError("error happened")
            }

            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if(response.isSuccessful) {
                    callback.onSuccess(response.body())
                    forecast.value = response.body()
            }

                //callback.onError("error happened")
            }

            /*override fun onSuccess(data: Forecast?) {
                forecast.value = data
                Log.e("OK", forecast.value.toString())
            }*/
        })
    }

    //fun returnData() = forecast.value

    fun ret() = fore
}