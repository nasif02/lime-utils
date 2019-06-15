package com.xlib.limeutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.xlib.limeutils.base.Contextor;
import com.xlib.limeutils.base.Lc;


/**
 * Created by Xplo on 1/3/2016.
 */
public class PrefUtils {

    private static final String TAG = PrefUtils.class.getSimpleName() + Lc.TAG_POSTFIX;
    private static Context context = Contextor.getInstance().getContext();

    //system code
    //private static final String PREF_NAME = "Default";
    //private static SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);   //custom
    private static SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);    //default
    //private static SharedPreferences.Editor ed = sp.edit();


    public static void putInt(String key, int value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key, value);
        ed.apply();

    }

    public static void putLong(String key, long value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putLong(key, value);
        ed.apply();

    }

    public static void putFloat(String key, float value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putFloat(key, value);
        ed.apply();

    }

    public static void putString(String key, String value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.apply();

    }

    public static void putBoolean(String key, boolean value) {

        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key, value);
        ed.apply();

    }


    public static int getInt(String key, int defValue) {

        return sp.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {

        return sp.getLong(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {

        return sp.getBoolean(key, defValue);
    }

    public static float getFloat(String key, float defValue) {

        return sp.getFloat(key, defValue);
    }

    public static String getString(String key, String defValue) {

        return sp.getString(key, defValue);
    }

    public static void remove(String key) {

        SharedPreferences.Editor ed = sp.edit();

        try {
            ed.remove(key);
            ed.apply();
            Log.d(TAG, key + " removed successfully");
        } catch (Exception e) {
            Log.e(TAG, key + " not removed successfully");
            e.printStackTrace();
        }

    }

    public static void clear() {

        SharedPreferences.Editor ed = sp.edit();
        try {
            ed.clear();
            //ed.commit();
            ed.apply();
            Log.d(TAG, "Preference cleared successfully");
        } catch (Exception e) {
            Log.e(TAG, "Preference not cleared successfully");
            e.printStackTrace();

        }
    }




}
