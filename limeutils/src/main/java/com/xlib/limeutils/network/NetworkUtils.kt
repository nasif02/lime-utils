package com.xlib.limeutils.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager


/**
 * Created by Xplo on 1/4/2016.
 */
@SuppressLint("MissingPermission")
object NetworkUtils {

    private const val TAG = "NetworkUtils"

    /**
     * Method to check internet is avalable
     * need permission in manifest
     *
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }

        }
        return false
    }


    fun getSimNumber(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        //String getSimSerialNumber = telephonyManager.getSimSerialNumber();

        return telephonyManager.line1Number
    }

    fun getSimSerial(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val getSimSerialNumber = telephonyManager.simSerialNumber
        val getSimNumber = telephonyManager.line1Number


        return getSimSerialNumber
    }

    fun getIMEI(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        return telephonyManager.deviceId
    }

}
