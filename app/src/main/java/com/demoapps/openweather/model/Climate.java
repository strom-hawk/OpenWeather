package com.demoapps.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
This class is used for response
*/

public class Climate {
    @SerializedName("main")
    @Expose
    private String climateTitle;

    @SerializedName("description")
    @Expose
    private String climateDescription;

    @SerializedName("icon")
    @Expose
    private String climateIcon;

    //getter climateTitle
    public String getClimateTitle() {
        return climateTitle;
    }

    //setter climateTitle
    public void setClimateTitle(String climateTitle) {
        this.climateTitle = climateTitle;
    }

    //getter climateDescription
    public String getClimateDescription() {
        return climateDescription;
    }

    //setter climateDescription
    public void setClimateDescription(String climateDescription) {
        this.climateDescription = climateDescription;
    }

    //getter climateIcon
    public String getClimateIcon() {
        return climateIcon;
    }

    //setter climateIcon
    public void setClimateIcon(String climateIcon) {
        this.climateIcon = climateIcon;
    }
}
