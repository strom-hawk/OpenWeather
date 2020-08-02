package com.demoapps.openweather.interfaces

import com.demoapps.openweather.model.OpenWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("APPID") app_id: String, @Query("q") location: String): Call<OpenWeatherResponse>
}