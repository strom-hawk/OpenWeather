package com.demoapps.openweather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demoapps.openweather.R
import io.reactivex.disposables.CompositeDisposable

abstract class ActivityBase: AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}