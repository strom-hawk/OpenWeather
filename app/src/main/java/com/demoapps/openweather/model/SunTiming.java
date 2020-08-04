package com.demoapps.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
This class is used for response
*/

public class SunTiming {
    @SerializedName("sunrise")
    @Expose
    private Long sunrise;

    @SerializedName("sunset")
    @Expose
    private Long sunset;

    //getter sunrise
    public Long getSunrise() {
        return sunrise;
    }

    //getter sunset
    public Long getSunset() {
        return sunset;
    }
}
