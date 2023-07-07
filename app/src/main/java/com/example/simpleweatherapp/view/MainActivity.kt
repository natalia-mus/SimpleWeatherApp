package com.example.simpleweatherapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.model.Forecast
import com.example.simpleweatherapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.forecast_parameter_tile.view.*
import kotlinx.android.synthetic.main.forecast_parameters.*
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
        updateView(false)
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
        temperature.text = forecast.main.temperature.toString()
        description.text = forecast.weather[0].description

        // humidity:
        humidity_tile.parameter_value.text = forecast.main.humidity.toString()
        humidity_tile.parameter_unit.visibility = View.VISIBLE
        humidity_tile.parameter_unit.text = getString(R.string.percent)
        humidity_tile.parameter_icon.setImageResource(R.drawable.ic_humidity)
        humidity_tile.parameter_name.text = getString(R.string.label_humidity)

        // pressure:
        pressure_tile.parameter_value.text = forecast.main.pressure.toString()
        pressure_tile.parameter_unit.visibility = View.VISIBLE
        pressure_tile.parameter_unit.text = getString(R.string.pressure_unit)
        pressure_tile.parameter_icon.setImageResource(R.drawable.ic_pressure)
        pressure_tile.parameter_name.text = getString(R.string.label_pressure)

        // feelsLike:
        feelsLike_tile.parameter_value.text = forecast.main.feelsLike.toString()
        feelsLike_tile.parameter_unit.visibility = View.VISIBLE
        feelsLike_tile.parameter_unit.text = getString(R.string.temperature_unit)
        feelsLike_tile.parameter_icon.setImageResource(R.drawable.ic_feels_like)
        feelsLike_tile.parameter_name.text = getString(R.string.label_feels_like)

        // temperatureMin:
        temperatureMin_tile.parameter_value.text = forecast.main.tempMin.toString()
        temperatureMin_tile.parameter_unit.visibility = View.VISIBLE
        temperatureMin_tile.parameter_unit.text = getString(R.string.temperature_unit)
        temperatureMin_tile.parameter_icon.setImageResource(R.drawable.ic_min_temperature)
        temperatureMin_tile.parameter_name.text = getString(R.string.label_temperature_min)

        // temperatureMax:
        temperatureMax_tile.parameter_value.text = forecast.main.tempMax.toString()
        temperatureMax_tile.parameter_unit.visibility = View.VISIBLE
        temperatureMax_tile.parameter_unit.text = getString(R.string.temperature_unit)
        temperatureMax_tile.parameter_icon.setImageResource(R.drawable.ic_max_temperature)
        temperatureMax_tile.parameter_name.text = getString(R.string.label_temperature_max)

        // clouds:
        clouds_tile.parameter_value.text = forecast.clouds.cloudiness.toString()
        clouds_tile.parameter_unit.visibility = View.VISIBLE
        clouds_tile.parameter_unit.text = getString(R.string.percent)
        clouds_tile.parameter_icon.setImageResource(R.drawable.ic_clouds)
        clouds_tile.parameter_name.text = getString(R.string.label_clouds)

        // wind:
        wind_tile.parameter_value.text = forecast.wind.speed.toString()
        wind_tile.parameter_unit.visibility = View.VISIBLE
        wind_tile.parameter_unit.text = getString(R.string.wind_unit)
        wind_tile.parameter_icon.setImageResource(R.drawable.ic_wind)
        wind_tile.parameter_name.text = getString(R.string.label_wind)

        // sunrise:
        val sunriseValue = prepareTime(forecast.sys.sunrise, forecast.timeZone)
        sunrise_tile.parameter_value.text = sunriseValue
        sunrise_tile.parameter_icon.setImageResource(R.drawable.ic_sunrise)
        sunrise_tile.parameter_name.text = getString(R.string.label_sunrise)

        // sunset:
        val sunsetValue = prepareTime(forecast.sys.sunset, forecast.timeZone)
        sunset_tile.parameter_value.text = sunsetValue
        sunset_tile.parameter_icon.setImageResource(R.drawable.ic_sunset)
        sunset_tile.parameter_name.text = getString(R.string.label_sunset)
    }

    private fun updateErrorInfo(status: Boolean) {
        if (status) {
            updateView(true)
            error_label.visibility = View.GONE

        } else {
            updateView(false)
            error_label.visibility = View.VISIBLE
        }
    }

    private fun updateView(visible: Boolean) {
        main_tile.visibility = if (visible) View.VISIBLE else View.GONE
        humidity_tile.visibility = if (visible) View.VISIBLE else View.GONE
        pressure_tile.visibility = if (visible) View.VISIBLE else View.GONE
        feelsLike_tile.visibility = if (visible) View.VISIBLE else View.GONE
        temperatureMin_tile.visibility = if (visible) View.VISIBLE else View.GONE
        temperatureMax_tile.visibility = if (visible) View.VISIBLE else View.GONE
        clouds_tile.visibility = if (visible) View.VISIBLE else View.GONE
        wind_tile.visibility = if (visible) View.VISIBLE else View.GONE
        sunrise_tile.visibility = if (visible) View.VISIBLE else View.GONE
        sunset_tile.visibility = if (visible) View.VISIBLE else View.GONE
    }

}
