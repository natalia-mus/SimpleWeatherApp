package com.example.simpleweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.Forecast

class MainActivityViewModel : ViewModel() {

    private val forecast = MutableLiveData<Forecast>()

    fun getData() {}

    fun returnData(): LiveData<Forecast> = forecast
}