package com.demoapps.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
This class is used for response
*/

public class WeatherDetails {
    @SerializedName("temp")
    @Expose
    private String currentTemperature;

    //getter currentTemperature
    public String getCurrentTemperature() {
        return currentTemperature;
    }

    //setter currentTemperature
    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
