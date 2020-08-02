package com.demoapps.openweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demoapps.openweather.model.OpenWeatherResponse
import com.demoapps.openweather.utils.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://api.openweathermap.org/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        getWeaterButton.setOnClickListener {
            val apiServices = RetrofitClient.getApiClient(baseUrl)
            apiServices?.getCurrentWeatherData("6ef89489ba6ce1fddb4d5041a0727a03", "bangalore")
                ?.enqueue(object : Callback<OpenWeatherResponse> {
                    override fun onFailure(call: Call<OpenWeatherResponse>, t: Throwable) {
                        getWeaterButton.text = "Api Failed"
                    }

                    override fun onResponse(
                        call: Call<OpenWeatherResponse>,
                        response: Response<OpenWeatherResponse>
                    ) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            getWeaterButton.text = responseBody.weatherDetails.currentTemperature
                        }
                    }
                })
        }
    }
}