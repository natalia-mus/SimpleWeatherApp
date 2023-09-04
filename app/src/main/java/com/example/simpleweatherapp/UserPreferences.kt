package com.example.simpleweatherapp

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {

    private const val CITY = "city"

    private var instance: SharedPreferences? = null


    fun getCity(context: Context): String? {
        return getInstance(context).getString(CITY, null)
    }

    fun saveCity(city: String, context: Context) {
        getInstance(context).edit().putString(CITY, city).apply()
    }

    private fun getInstance(context: Context): SharedPreferences {
        if (instance == null) {
            instance = context.getSharedPreferences(getUserPreferencesName(context), Context.MODE_PRIVATE)
        }
        return instance!!
    }

    private fun getUserPreferencesName(context: Context): String {
        val name = StringBuilder()
        name.append(context.packageName)
        name.append(".")
        name.append("user_preferences")
        return name.toString()
    }
}