package com.example.simpleweatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.model.api.Repository
import com.example.simpleweatherapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val viewmodel = MainActivityViewModel()
        //viewmodel.getData()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getData()
        //viewModel.forecast.observe(this, Observer { Log.e("MainActivity", it.toString()) })
        viewModel.forecast.observe(this, Observer { textView_temperature.text = it!!.main.temperature.toString() })
        //viewModel.forecast.observe(this, Observer {Log.e("MainActivity", it.toString())})


        /*val repo = Repository()
        repo.getDataFromAPI()
        repo.returnData()*/
    }
}