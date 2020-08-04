package com.demoapps.openweather.interfaces

import com.demoapps.openweather.model.OpenWeatherResponse

interface WeatherDetailsFlowCallBack {
    fun onApiCalled()
    fun onApiSuccess(openWeatherResponse: OpenWeatherResponse)
    fun onApiFailed(error: Throwable)
}