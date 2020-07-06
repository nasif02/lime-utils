package com.xlib.limeutils.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Copyright 2019 (C) xplo
 * <p>
 * Created  : 2019-11-08
 * Updated  :
 * Author   : xplo
 * Desc     : com.xlib.limeutils.utils
 * Comment  :
 */
public class ResoUtils {

    /**
     * Convert DP to Pixel
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dip2px(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5);
    }

    /**
     * Convert Pixel to DP
     *
     * @param px
     * @param context
     * @return
     */
    public static float px2dip(Context context, int px) {
        return (float) px / context.getResources().getDisplayMetrics().density;
    }

    public static int getDeviceHeight(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getDeviceWidth(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDeviceHeightByRatio(Context context, double ratioValue) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        float pxHeight = (float) (ratioValue * (double) ((float) displayMetrics.widthPixels));
        return (int) pxHeight;
    }

    public static String getDensityName(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }


}
