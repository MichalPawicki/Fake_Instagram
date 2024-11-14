package com.example.newapplication.sharedPref

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPref @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun markAsFetched() {
        val sharedPref = sharedPreferences
        val name = sharedPref.edit()

        name.putBoolean("random_key", true)
        name.apply()

    }

    fun isFetched(): Boolean {
        val sharedPref = sharedPreferences
        val defaultValue = false
        val isFetched = sharedPref.getBoolean("random_key", defaultValue)
        return isFetched
    }

}


