package com.demoapps.openweather.view

import android.os.Bundle
import com.demoapps.openweather.R
import com.demoapps.openweather.interfaces.WeatherDetailsFlowCallBack
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.ApplicationConstants
import com.demoapps.openweather.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_homescreen.*

/*
*This class is the landing page of the Open Weather application
*/

class HomeScreen : ActivityBase(), WeatherDetailsFlowCallBack {
    private var homeScreenViewModel: HomeScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)
        init()
        bindView()
    }

    private fun init() {
        homeScreenViewModel = HomeScreenViewModel(this)
    }

    private fun bindView() {
        getWeaterButton.setOnClickListener {
            homeScreenViewModel?.getCityWeatherDetails()
        }
    }

    override fun onApiCalled() {}

    override fun onApiSuccess(openWeatherResponse: OpenWeatherResponse) {
        getWeaterButton.text = openWeatherResponse.weatherDetails.currentTemperature
        val tempInKelvin = openWeatherResponse.weatherDetails.currentTemperature.toFloat()
        val tempInCelsius = convertKelvinToCelcius(tempInKelvin).toString()
            .substringBefore(ApplicationConstants.DOT_DELIMITTER) + ApplicationConstants.CELSIUS_REPRESENTATION
        getWeaterButton.text = tempInCelsius
    }

    override fun onApiFailed(error: Throwable) {
        getWeaterButton.text = getString(R.string.something_went_wrong)
    }

    private fun convertKelvinToCelcius(tempInKelvin: Float): Float {
        return tempInKelvin - ApplicationConstants.KELVIN_DIFFERENCE
    }
}