package com.example.simpleweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.model.api.Repository
import com.example.simpleweatherapp.model.api.RepositoryCallback

class MainActivityViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val forecast = MutableLiveData<Forecast>()

    fun getData(city: String) {
        Repository.getDataFromAPI(city, object : RepositoryCallback<Forecast> {
            override fun onError(error: String?) {
                status.value = false
            }

            override fun onSuccess(data: Forecast?) {
                status.value = true
                forecast.value = data
            }
        })
    }

}