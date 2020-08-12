package com.demoapps.openweather

import com.demoapps.openweather.interfaces.WeatherDetailsFlowCallBack
import com.demoapps.openweather.utils.ApplicationConstants
import com.demoapps.openweather.utils.RetrofitClient
import com.demoapps.openweather.utils.Router
import com.demoapps.openweather.view.HomeScreen
import com.demoapps.openweather.viewmodel.HomeScreenViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class BasicUnitTestCases {
    lateinit var homeScreen: HomeScreen
    val cityName = "Lohardaga"

    @Before
    fun setUp() {
        homeScreen = HomeScreen()
    }

    @Test
    fun testConversionToCelsius() {
        val tempInCelsius = homeScreen.convertKelvinToCelcius(273.15F)
        Assert.assertEquals("0.0", tempInCelsius.toString())
    }

    @Test
    fun testValidateAndProceed() {
        Assert.assertEquals(true, homeScreen.validateAndProceed(cityName))
    }

    @Test
    fun testValidateAndProceedWithEmptyCity() {
        Assert.assertEquals(false, homeScreen.validateAndProceed(ApplicationConstants.EMPTY_STRING))
    }

    @Test
    fun testRouterVariables(){
        Router.city = cityName
        Router.currentLatitude = "0.0"
        Router.currentLongitude = "0.0"

        Assert.assertEquals(cityName, Router.city)
        Assert.assertEquals("0.0", Router.currentLatitude)
        Assert.assertEquals("0.0", Router.currentLongitude)
    }


}