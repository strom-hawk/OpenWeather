package com.demoapps.openweather.model;

/*
This class is used for response
*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OpenWeatherResponse {
    @SerializedName("main")
    @Expose
    private WeatherDetails weatherDetails;

    @SerializedName("message")
    @Expose
    private String txnMessage;

    @SerializedName("name")
    @Expose
    private String city;

    @SerializedName("wind")
    @Expose
    private WindDetail windDetail;

    @SerializedName("cod")
    @Expose
    private String txnStatus;

    @SerializedName("weather")
    @Expose
    private ArrayList<Climate> climate;

    @SerializedName("sys")
    @Expose
    private SunTiming sunTiming;

    //getter weatherDetails
    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    //setter weatherDetails
    public void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    //getter city
    public String getCity() {
        return city;
    }

    //setter city
    public void setCity(String city) {
        this.city = city;
    }

    //getter windDetail
    public WindDetail getWindDetail() {
        return windDetail;
    }

    //setter windDetail
    public void setWindDetail(WindDetail windDetail) {
        this.windDetail = windDetail;
    }

    //getter txnMessage
    public String getTxnMessage() {
        return txnMessage;
    }

    //setter txnMessage
    public void setTxnMessage(String txnMessage) {
        this.txnMessage = txnMessage;
    }

    //getter txnStatus
    public String getTxnStatus() {
        return txnStatus;
    }

    //setter txnStatus
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    //getter climate
    public ArrayList<Climate> getClimate() {
        return climate;
    }

    //setter climate
    public void setClimate(ArrayList<Climate> climate) {
        this.climate = climate;
    }

    //getter sunTiming
    public SunTiming getSunTiming() {
        return sunTiming;
    }

    //setter sunTiming
    public void setSunTiming(SunTiming sunTiming) {
        this.sunTiming = sunTiming;
    }
}
