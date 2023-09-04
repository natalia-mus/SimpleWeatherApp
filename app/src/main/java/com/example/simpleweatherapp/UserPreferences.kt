package com.example.simpleweatherapp

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private companion object {
        const val CITY = "city"
        const val USER_PREFERENCES = "user_preferences"
    }

    private val instance: SharedPreferences

    init {
        instance = context.getSharedPreferences(getUserPreferencesName(context), Context.MODE_PRIVATE)
    }

    fun getCity(): String? {
        return instance.getString(CITY, null)
    }

    fun saveCity(city: String) {
        instance.edit().putString(CITY, city).apply()
    }

    private fun getUserPreferencesName(context: Context): String {
        val name = StringBuilder()
        name.append(context.packageName)
        name.append(".")
        name.append(USER_PREFERENCES)
        return name.toString()
    }
}