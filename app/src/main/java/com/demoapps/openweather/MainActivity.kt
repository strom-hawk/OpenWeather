package com.demoapps.openweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://api.openweathermap.org/"
    private val KEY = "6ef89489ba6ce1fddb4d5041a0727a03"
    private val LOCATION = "Bangalore"
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        getWeaterButton.setOnClickListener {
            throughRxJava()
        }
    }

    private fun throughRxJava() {
        compositeDisposable = CompositeDisposable()
        val apiServices = RetrofitClient.getApiClient(baseUrl)
        if (apiServices != null) {
            compositeDisposable?.add(apiServices.getCurrentWeatherData(KEY, LOCATION)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    handleSuccess(response)
                }, { error ->
                    handleError(error)
                })
            )
        }
    }

    private fun handleSuccess(response: OpenWeatherResponse) {
        getWeaterButton.text = response.weatherDetails.currentTemperature

    }

    private fun handleError(error: Throwable) {
        getWeaterButton.text = "Api Failed"
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}