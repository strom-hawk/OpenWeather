package com.demoapps.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
This class is used for response
*/

public class WindDetail {
    @SerializedName("speed")
    @Expose
    private String windSpeed;

    @SerializedName("deg")
    @Expose
    private String degree;

    //getter windSpeed
    public String getWindSpeed() {
        return windSpeed;
    }

    //setter windSpeed
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    //getter degree
    public String getDegree() {
        return degree;
    }

    //setter degree
    public void setDegree(String degree) {
        this.degree = degree;
    }
}
