package com.demoapps.openweather.model;

/*
This class is used for response
*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeatherResponse {
    @SerializedName("main")
    @Expose
    private WeatherDetails weatherDetails;

    //getter weatherDetails
    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    //setter weatherDetails
    public void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }
}
