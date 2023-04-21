package com.example.simpleweatherapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setListeners()
        setObservers()
    }

    private fun getTimeZone(timeZoneMilliseconds: Long): String {
        val timeZoneHours = timeZoneMilliseconds / 3600
        val sign = if (timeZoneHours > 0) "+" else ""
        return "GMT$sign$timeZoneHours:00"
    }

    private fun prepareTime(timestampInMilliseconds: Long, timeZoneInMilliseconds: Long): String {
        val date = Date(timestampInMilliseconds * 1000)
        val dateFormat = SimpleDateFormat("HH:mm")
        val timeZone = getTimeZone(timeZoneInMilliseconds)
        dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
        return dateFormat.format(date)
    }

    private fun setListeners() {
        button_search.setOnClickListener() {
            viewModel.getData(city.text.toString())
        }
    }

    private fun setObservers() {
        viewModel.status.observe(this) { updateErrorInfo(it) }
        viewModel.forecast.observe(this) { updateData(it) }
    }

    private fun updateData(forecast: Forecast) {
        val iconSource = "http://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"

        Glide.with(this)
            .load(iconSource)
            .override(80, 80)
            .into(icon)

        temperature.text = forecast.main.temperature.toString()
        description.text = forecast.weather[0].description
        humidity.text = forecast.main.humidity.toString()
        pressure.text = forecast.main.pressure.toString()
        feelsLike.text = forecast.main.feelsLike.toString()
        temperatureMin.text = forecast.main.tempMin.toString()
        temperatureMax.text = forecast.main.tempMax.toString()
        clouds.text = forecast.clouds.cloudiness.toString()
        wind.text = forecast.wind.speed.toString()

        val sunriseValue = prepareTime(forecast.sys.sunrise, forecast.timeZone)
        val sunsetValue = prepareTime(forecast.sys.sunset, forecast.timeZone)
        sunrise.text = sunriseValue
        sunset.text = sunsetValue

        humidity_unit.visibility = View.VISIBLE
        pressure_unit.visibility = View.VISIBLE
        feelsLike_unit.visibility = View.VISIBLE
        temperatureMin_unit.visibility = View.VISIBLE
        temperatureMax_unit.visibility = View.VISIBLE
        clouds_unit.visibility = View.VISIBLE
        wind_unit.visibility = View.VISIBLE
    }

    private fun updateErrorInfo(status: Boolean) {
        if (status) {
            error_label.visibility = View.GONE
        } else {
            error_label.visibility = View.VISIBLE
        }
    }

}
