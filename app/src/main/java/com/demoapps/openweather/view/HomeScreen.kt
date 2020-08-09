package com.demoapps.openweather.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.demoapps.openweather.R
import com.demoapps.openweather.interfaces.WeatherDetailsFlowCallBack
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.ApplicationConstants
import com.demoapps.openweather.utils.CommonUtils
import com.demoapps.openweather.utils.Router
import com.demoapps.openweather.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_homescreen.*
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*


/*
*This class is the landing page of the Open Weather application
*/

class HomeScreen : ActivityBase(), WeatherDetailsFlowCallBack {
    private val REQUEST_LOCATION = 1
    private val FINE_LOCATION_PERMISSION = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
    private var homeScreenViewModel: HomeScreenViewModel? = null
    private lateinit var locationManager : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_homescreen)
        init()
        bindView()
    }

    private fun init() {
        homeScreenViewModel = HomeScreenViewModel(this)
        initLiveData()
        getLatLongFlow()
    }

    private fun bindView() {
        getWeaterButton.setOnClickListener {
            validateAndProceed()
        }
    }

    private fun validateAndProceed(){
        if(etCity.text.toString().equals(ApplicationConstants.EMPTY_STRING)){
            CommonUtils.showAlertDialog(this, getString(R.string.invalid_city_name), false)
        }else{
            Router.city = etCity.text.toString()
            homeScreenViewModel?.getCityWeatherDetails()
        }
    }

    override fun onApiCalled() {}

    override fun onApiSuccess(openWeatherResponse: OpenWeatherResponse) {
        if (openWeatherResponse.txnStatus.equals(ApplicationConstants.TXN_STATUS_200)) {
            val tempInKelvin = openWeatherResponse.weatherDetails.currentTemperature.toFloat()
            val tempInCelsius = convertKelvinToCelcius(tempInKelvin).toString()
                .substringBefore(ApplicationConstants.DOT_DELIMITTER) + ApplicationConstants.CELSIUS_REPRESENTATION

            populateDate(openWeatherResponse, tempInCelsius)
        } else if (openWeatherResponse.txnStatus.equals(ApplicationConstants.TXN_STATUS_404)) {
            CommonUtils.showAlertDialog(this, openWeatherResponse.txnMessage, false)
        } else {
            CommonUtils.showAlertDialog(this, getString(R.string.something_went_wrong), false)
        }
    }

    override fun onApiFailed(error: Throwable) {
        if((error as HttpException).code().equals(ApplicationConstants.TXN_STATUS_404_NUMBER)){
            CommonUtils.showAlertDialog(this, error.message(), false)
        }else{
            CommonUtils.showAlertDialog(this, getString(R.string.something_went_wrong), false)
        }
    }

    private fun convertKelvinToCelcius(tempInKelvin: Float): Float {
        return tempInKelvin - ApplicationConstants.KELVIN_DIFFERENCE
    }

    private fun populateDate(openWeatherResponse: OpenWeatherResponse, tempInCelsius: String){
        tvLocation.text = openWeatherResponse.city
        tvDateTime.text = CommonUtils.getCurrentDateAndTime()
        tvCurrentTemperature.text = tempInCelsius
        tvWeaterDescription.text = openWeatherResponse.climate[0].climateTitle
    }

    private fun getLatLongFlow(){
        ActivityCompat.requestPermissions(this, FINE_LOCATION_PERMISSION, REQUEST_LOCATION);
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            CommonUtils.showGPSDialog(this, getString(R.string.location_error), true)
        }else{
            getLatLong()
        }
    }

    private fun initLiveData(){
        val latLongObservable = Observer<Boolean>{
            if(Router.locationChanged.value!!){
                Router.locationChanged.value = false
                homeScreenViewModel?.getLatLongWeatherDetails()
            }
        }

        Router.locationChanged.observe(this, latLongObservable)
    }

    private fun getLatLong(){
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, FINE_LOCATION_PERMISSION, REQUEST_LOCATION);
        } else {
            val locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(locationGPS != null){
                val latitude = locationGPS.getLatitude()
                val longitude = locationGPS.getLongitude()
                Router.currentLatitude = latitude.toString()
                Router.currentLongitude = longitude.toString()
                Router.locationChanged.value = true
            }else{
                CommonUtils.showAlertDialog(this, getString(R.string.location_error), false)
            }
        }
    }
}