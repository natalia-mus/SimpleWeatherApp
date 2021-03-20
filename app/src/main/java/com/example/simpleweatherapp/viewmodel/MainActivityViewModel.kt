package com.example.simpleweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.model.api.Repository
import com.example.simpleweatherapp.model.api.RepositoryCallback

class MainActivityViewModel : ViewModel() {

    private val model = Repository()

    val forecast = MutableLiveData<Forecast>()

    fun getData() {
        model.getDataFromAPI(object : RepositoryCallback<Forecast> {
            override fun onError(error: String?) {
                Log.e("ViewModel", "ERROR")
            }

            override fun onSuccess(data: Forecast?) {
                forecast.value = data
            }
        })
    }

}