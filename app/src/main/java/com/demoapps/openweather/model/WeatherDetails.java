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

    @SerializedName("pressure")
    @Expose
    private String pressure;

    @SerializedName("humidity")
    @Expose
    private String humidity;

    //getter currentTemperature
    public String getCurrentTemperature() {
        return currentTemperature;
    }

    //setter currentTemperature
    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    //getter pressure
    public String getPressure() {
        return pressure;
    }

    //setter pressure
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    //getter humidity
    public String getHumidity() {
        return humidity;
    }
    //setter humidity
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
