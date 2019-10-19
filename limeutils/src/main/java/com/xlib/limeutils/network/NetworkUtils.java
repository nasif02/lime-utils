package com.xlib.limeutils.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import com.xlib.limeutils.base.Contextor;


/**
 * Created by Xplo on 1/4/2016.
 */
public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    /**
     * Method to check internet is avalable
     * need permission in manifest
     *
     * @param context
     * @return
     */
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            @SuppressLint("MissingPermission") NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public static String getSimNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //String getSimSerialNumber = telephonyManager.getSimSerialNumber();
        @SuppressLint("MissingPermission") String getSimNumber = telephonyManager.getLine1Number();

        return getSimNumber;
    }

    public static String getSimSerial(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String getSimSerialNumber = telephonyManager.getSimSerialNumber();
        @SuppressLint("MissingPermission") String getSimNumber = telephonyManager.getLine1Number();


        return getSimSerialNumber;
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String IMEI = telephonyManager.getDeviceId();

        return IMEI;
    }

}
