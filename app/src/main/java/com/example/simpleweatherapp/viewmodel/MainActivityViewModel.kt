package com.example.simpleweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.model.api.Repository
import com.example.simpleweatherapp.model.api.RepositoryCallback

class MainActivityViewModel : ViewModel() {

    private val model = Repository()

    val forecast = MutableLiveData<Forecast>()
    //val fore = MutableLiveData<String>()

    fun getData() {
        model.getDataFromAPI(object: RepositoryCallback<Forecast> {
            override fun onError(error: String?) {
                Log.e("ViewModel", "ERROR")
            }

            override fun onSuccess(data: Forecast?) {
                forecast.value = data
            }
        })


        //forecast.value = model.returnData()
        //forecast.value = model.returnData()
        Log.e("viewmodel", forecast.value.toString())
    }

    //fun returnData(): LiveData<Forecast> = forecast
}