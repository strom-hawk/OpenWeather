package com.demoapps.openweather.utils

/*
*This object is used for storing application constants
*/

object ApplicationConstants {
    val BASE_URL = "https://api.openweathermap.org/"
    val API_KEY: String = "6ef89489ba6ce1fddb4d5041a0727a03"
    val DOT_DELIMITTER = "."
    val CELSIUS_REPRESENTATION = "°C"
    val TXN_STATUS_200 = "200"
    val TXN_STATUS_404 = "404"
    val TXN_STATUS_404_NUMBER = 404
    val KELVIN_DIFFERENCE = 273.15F
    val EMPTY_STRING = ""
    val DATE_FORMAT = "dd/M/yyyy hh:mm"

    val CLEAR= "Clear"
    val RAIN = "Rain"
    val THUNDERSTORM = "Thunderstorm"
    val MORNING_SHIFT = 6
    val EVENING_SHIFT = 18
}