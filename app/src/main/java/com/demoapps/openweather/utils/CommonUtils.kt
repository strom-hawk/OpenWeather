package com.demoapps.openweather.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat.startActivity
import com.demoapps.openweather.R
import kotlinx.android.synthetic.main.alert_dialog_layout.*
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    private lateinit var alertDialog : Dialog

    fun showAlertDialog(activity: Activity, messge: String, negativeButtonVisibility: Boolean){
        alertDialog = showCustomDialog(activity, R.layout.alert_dialog_layout)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.dialogMessage.text = messge
        alertDialog.positiveButton.text  = activity.resources.getString(R.string.positive_button)
        alertDialog.positiveButton.setOnClickListener {
            alertDialog.dismiss()
        }
        if(negativeButtonVisibility){
            alertDialog.negativeButton.visibility = View.VISIBLE
        }else{
            alertDialog.negativeButton.visibility = View.GONE
        }

        alertDialog.show()
    }

    fun showGPSDialog(activity: Activity, messge: String, negativeButtonVisibility: Boolean){
        alertDialog = showCustomDialog(activity, R.layout.alert_dialog_layout)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.dialogMessage.text = messge
        alertDialog.positiveButton.text  = activity.resources.getString(R.string.positive_button)
        alertDialog.positiveButton.setOnClickListener {
            Router.startLocationSettingsFlow(activity)
            alertDialog.dismiss()
        }

        alertDialog.negativeButton.setOnClickListener {
            alertDialog.dismiss()
        }

        if(negativeButtonVisibility){
            alertDialog.negativeButton.visibility = View.VISIBLE
        }else{
            alertDialog.negativeButton.visibility = View.GONE
        }

        alertDialog.show()
    }

    fun showCustomDialog(context: Context, layout: Int): Dialog {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        alertDialog = Dialog(context)
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(false)
        alertDialog.setContentView(layout)
        alertDialog.window?.setLayout(screenWidth-200, ViewGroup.LayoutParams.WRAP_CONTENT)
        alertDialog.window?.setGravity(Gravity.CENTER)
        return alertDialog
    }

    fun getCurrentDateAndTime() : String {
        val simpleDateFormat = SimpleDateFormat(ApplicationConstants.DATE_FORMAT, Locale.ENGLISH)
        val currentDate = simpleDateFormat.format(Date())
        return currentDate
    }
}