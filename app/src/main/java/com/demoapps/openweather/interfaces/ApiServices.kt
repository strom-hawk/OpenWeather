package com.demoapps.openweather.interfaces

import com.demoapps.openweather.model.OpenWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(
        @Query("APPID") app_id: String,
        @Query("q") location: String
    ): Observable<OpenWeatherResponse>

    @GET("data/2.5/weather?")
    fun getCurrentLocationWeather(
        @Query("APPID") app_id: String,
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Observable<OpenWeatherResponse>
}