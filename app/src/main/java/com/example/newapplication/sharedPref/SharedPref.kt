package com.example.newapplication.sharedPref

import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


class SharedPref @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun markAsFetched() {
        val sharedPref = sharedPreferences
        val fetchedStatus = sharedPref.edit()

        fetchedStatus.putBoolean("random_key", true)
        fetchedStatus.apply()

    }

    fun isFetched(): Boolean {
        val sharedPref = sharedPreferences
        val defaultValue = false
        val isFetched = sharedPref.getBoolean("random_key", defaultValue)
        return isFetched
    }

}


