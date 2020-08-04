package com.demoapps.openweather.view

import android.os.Bundle
import com.demoapps.openweather.R
import com.demoapps.openweather.interfaces.WeatherDetailsFlowCallBack
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.ApplicationConstants
import com.demoapps.openweather.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_homescreen.*
import retrofit2.HttpException

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
            homeScreenViewModel?.getCityWeatherDetails(etCity.text.toString())
        }
    }

    override fun onApiCalled() {}

    override fun onApiSuccess(openWeatherResponse: OpenWeatherResponse) {
        if(openWeatherResponse.txnStatus.equals(ApplicationConstants.TXN_STATUS_200)){
            val tempInKelvin = openWeatherResponse.weatherDetails.currentTemperature.toFloat()
            val tempInCelsius = convertKelvinToCelcius(tempInKelvin).toString()
                .substringBefore(ApplicationConstants.DOT_DELIMITTER) + ApplicationConstants.CELSIUS_REPRESENTATION
            getWeaterButton.text = tempInCelsius
        }else if(openWeatherResponse.txnStatus.equals(ApplicationConstants.TXN_STATUS_404)){
            getWeaterButton.text = openWeatherResponse.txnMessage
        }else{
            getWeaterButton.text = getString(R.string.something_went_wrong)
        }
    }

    override fun onApiFailed(error: Throwable) {

        if((error as HttpException).code().equals(ApplicationConstants.TXN_STATUS_404_NUMBER)){
            getWeaterButton.text = error.message()
        }else{
            getWeaterButton.text = getString(R.string.something_went_wrong)
        }
    }

    private fun convertKelvinToCelcius(tempInKelvin: Float): Float {
        return tempInKelvin - ApplicationConstants.KELVIN_DIFFERENCE
    }
}