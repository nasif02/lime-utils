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
    //private final static String TAG = DbgUtils.getTag(NetworkTools.class.getSimpleName());

    private static Context context = Contextor.getInstance().getContext();


    /**
     * Method to check internet is avalable
     * need permission in manifest
     *
     * @param context
     * @return
     */
    public static boolean isConnectingToInternet(Context context) {
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


    public static String getSimNumber() {
        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //String getSimSerialNumber = telemamanger.getSimSerialNumber();
        @SuppressLint("MissingPermission") String getSimNumber = telemamanger.getLine1Number();

        return getSimNumber;
    }

    public static String getSimSerial() {
        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String getSimSerialNumber = telemamanger.getSimSerialNumber();
        @SuppressLint("MissingPermission") String getSimNumber = telemamanger.getLine1Number();


        return getSimSerialNumber;
    }

    public static String getIMEI() {
        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String IMEI = telemamanger.getDeviceId();

        return IMEI;
    }

    public static String getUserName() {

        String s = null;
        Cursor c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        int count = c.getCount();
        String[] columnNames = c.getColumnNames();
        boolean b = c.moveToFirst();
        int position = c.getPosition();
        if (count == 1 && position == 0) {
            for (int j = 0; j < columnNames.length; j++) {
                String columnName = columnNames[j];
                String columnValue = c.getString(c.getColumnIndex(columnName));
                s = columnValue;
            }
        }
        c.close();

        return s;

    }


}
