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

    private fun setObservers() {
        viewModel.status.observe(this) { updateErrorInfo(it) }
        viewModel.forecast.observe(this) { updateData(it) }
    }

    private fun setListeners() {
        button_search.setOnClickListener() {
            viewModel.getData(editText_city.text.toString())
        }
    }

    private fun updateErrorInfo(status: Boolean) {
        if (status) {
            error_label.visibility = View.GONE
        } else {
            error_label.visibility = View.VISIBLE
        }
    }

    private fun updateData(forecast: Forecast) {
        val icon = "http://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"

        Glide.with(this)
            .load(icon)
            .override(80, 80)
            .into(imageView_icon)

        textView_temperature.text = forecast.main.temperature.toString()
        textView_description.text = forecast.weather[0].description
        textView_humidity.text = forecast.main.humidity.toString()
        textView_pressure.text = forecast.main.pressure.toString()
        textView_feelsLike.text = forecast.main.feelsLike.toString()
        textView_temperatureMin.text = forecast.main.tempMin.toString()
        textView_temperatureMax.text = forecast.main.tempMax.toString()
        textView_clouds.text = forecast.clouds.cloudiness.toString()
        textView_wind.text = forecast.wind.speed.toString()

        val sunrise = prepareTime(forecast.sys.sunrise, forecast.timeZone)
        val sunset = prepareTime(forecast.sys.sunset, forecast.timeZone)
        textView_sunrise.text = sunrise
        textView_sunset.text = sunset

        textView_humidity_unit.visibility = View.VISIBLE
        textView_pressure_unit.visibility = View.VISIBLE
        textView_feelsLike_unit.visibility = View.VISIBLE
        textView_temperatureMin_unit.visibility = View.VISIBLE
        textView_temperatureMax_unit.visibility = View.VISIBLE
        textView_clouds_unit.visibility = View.VISIBLE
        textView_wind_unit.visibility = View.VISIBLE
    }

    private fun prepareTime(timestampInMilliseconds: Long, timeZoneInMilliseconds: Long): String {
        val date = Date(timestampInMilliseconds * 1000)
        val dateFormat = SimpleDateFormat("HH:mm")
        val timeZone = getTimeZone(timeZoneInMilliseconds)
        dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
        return dateFormat.format(date)
    }

    private fun getTimeZone(timeZoneMilliseconds: Long): String {
        val timeZoneHours = timeZoneMilliseconds / 3600
        val sign = if (timeZoneHours > 0) "+" else ""
        return "GMT$sign$timeZoneHours:00"
    }

}
