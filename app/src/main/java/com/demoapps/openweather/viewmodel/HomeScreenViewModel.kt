package com.demoapps.openweather.viewmodel

import com.demoapps.openweather.interfaces.WeatherDetailsFlowCallBack
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.ApplicationConstants
import com.demoapps.openweather.utils.RetrofitClient
import com.demoapps.openweather.utils.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
*This class is used as view model of home screen
*/

class HomeScreenViewModel(
    private var weatherDetailsFlowCallBack: WeatherDetailsFlowCallBack
) : BaseViewModel() {

    fun getCityWeatherDetails() {
        val apiServices = RetrofitClient.getApiClient(ApplicationConstants.BASE_URL)
        if (apiServices != null) {
            compositeDisposable?.add(apiServices.getCurrentWeatherData(
                ApplicationConstants.API_KEY,
                Router.city
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe({ response ->
                    handleResponse(response)
                }, { error ->
                    handleError(error)
                })
            )
        }
    }

    private fun handleResponse(openWeatherResponse: OpenWeatherResponse) {
        weatherDetailsFlowCallBack.onApiSuccess(openWeatherResponse)
    }

    private fun handleError(error: Throwable) {
        weatherDetailsFlowCallBack.onApiFailed(error)
    }
}