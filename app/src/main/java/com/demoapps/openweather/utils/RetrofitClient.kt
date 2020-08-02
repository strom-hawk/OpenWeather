package com.demoapps.openweather.utils

import com.demoapps.openweather.interfaces.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private var okHttpClient: OkHttpClient? =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    fun getApiClient(url: String): ApiServices? {
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(okHttpClient)
                .build()
        }
        return retrofit?.create(ApiServices::class.java)
    }
}