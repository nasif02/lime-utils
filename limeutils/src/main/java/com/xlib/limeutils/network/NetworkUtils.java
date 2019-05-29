package com.xlib.limeutils.network;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;


/**
 * Created by Xplo on 1/4/2016.
 */
public class NetworkUtils {
    //private final static String TAG = DbgUtils.getTag(NetworkTools.class.getSimpleName());

    //private static Context mContext = MainApplication.getContext();

    public static boolean isConnectingToInternet(Context mContext) {
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public static String getSimNumber(Context mContext) {
        TelephonyManager telemamanger = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        //String getSimSerialNumber = telemamanger.getSimSerialNumber();
        String getSimNumber = telemamanger.getLine1Number();

        return getSimNumber;
    }

    public static String getSimSerial(Context mContext) {
        TelephonyManager telemamanger = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();
        String getSimNumber = telemamanger.getLine1Number();


        return getSimSerialNumber;
    }

    public static String getIMEI(Context mContext) {
        TelephonyManager telemamanger = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telemamanger.getDeviceId();

        return IMEI;
    }

    public static String getUserName(Context mContext) {

        String s = null;
        Cursor c = mContext.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
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
