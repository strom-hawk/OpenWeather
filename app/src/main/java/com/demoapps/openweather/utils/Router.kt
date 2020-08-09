package com.demoapps.openweather.utils

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity

object Router {
    var city = ""
    var currentLatitude = ""
    var currentLongitude = ""

    fun startLocationSettingsFlow(activity: Activity){
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(activity, intent, null)
    }
}