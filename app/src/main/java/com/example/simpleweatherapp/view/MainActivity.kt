package com.example.simpleweatherapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getData(editText_city.text.toString())

        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.forecast.observe(
            this,
            Observer { updateData(it) })
    }

    private fun setListeners() {
        button_search.setOnClickListener() {
            viewModel.getData(editText_city.text.toString())
        }
    }

    private fun updateData(forecast: Forecast) {

        val icon = "http://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"

        // AndroidMainfest.xml >> aplication section >> add "android:usesCleartextTraffic="true""
        Glide.with(this)
            .load(icon)
            .override(80, 80)
            .into(imageView_icon)

        textView_temperature.text = forecast.main.temperature.toString()
        textView_description.text = forecast.weather[0].description
        textView_humidity.text = forecast.main.humidity.toString()
        textView_pressure.text = forecast.main.pressure.toString()

        textView_humidity_unit.visibility = View.VISIBLE
        textView_pressure_unit.visibility = View.VISIBLE
    }
}
