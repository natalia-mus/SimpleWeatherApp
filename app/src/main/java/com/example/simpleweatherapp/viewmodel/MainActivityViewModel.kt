package com.example.simpleweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.model.api.Repository
import com.example.simpleweatherapp.model.api.RepositoryCallback

class MainActivityViewModel : ViewModel() {

    val forecast = MutableLiveData<Forecast>()

    fun getData(city: String) {
        Repository.getDataFromAPI(city, object : RepositoryCallback<Forecast> {
            override fun onError(error: String?) {
                Log.e("ViewModel", "ERROR")
            }

            override fun onSuccess(data: Forecast?) {
                forecast.value = data
            }
        })
    }

}