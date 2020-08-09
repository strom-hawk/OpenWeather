package com.demoapps.openweather.viewmodel

import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/*
*This class is used as base view model for every viewmodel
*/

abstract class BaseViewModel : ViewModel() {
    protected var compositeDisposable: CompositeDisposable? = null


    init {
        getCompositeDisposable()
    }

    private fun getCompositeDisposable(){
        if(compositeDisposable == null){
            compositeDisposable = CompositeDisposable()
        }
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }
}