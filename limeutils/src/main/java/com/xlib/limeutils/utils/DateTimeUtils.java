package com.xlib.limeutils.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright 2019 (C) xplo
 * <p>
 * Created  : 2020-02-02
 * Updated  :
 * Author   : Nasif Ahmed
 * Desc     :
 * Comment  :
 */
public class DateTimeUtils {

    private static final String TAG = "DateTimeUtils";

    /**
     * Method to calculate day difference
     *
     * @param fromDate
     * @param toDate
     * @return int
     */
    public static int getDayDifference(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null)
            return 0;

        int day = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        Log.d(TAG, "getDayDifference: day: " + day);
        return day;
    }

    /**
     * Method to check is date pass threshold
     *
     * @param prevDate  date
     * @param currDate  date
     * @param threshold int
     * @return boolean
     */
    public static boolean isDatePassThreshold(Date prevDate, Date currDate, int threshold) {
        if (getDayDifference(prevDate, currDate) >= threshold) {
            return true;
        }
        return false;
    }

    /**
     * Method to get current date as string
     *
     * @param sdf SimpleDateFormat
     * @return current date as string
     */
    public static String getCurrentDate(SimpleDateFormat sdf) {

        //SimpleDateFormat sdf = getDateFormat();

        String currDateText = sdf.format(new Date());
        Log.d(TAG, "getCurrentDate: currDateText: " + currDateText);

        return currDateText;
    }

    /**
     * Method to get date format
     *
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+6:00"));
        return sdf;
    }

    /**
     * Method to get timestamp
     *
     * @return a formatted timestamp
     */
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        String currDateText = sdf.format(new Date());
        Log.d(TAG, "getTimeStamp: currDateText: " + currDateText);
        return currDateText;
    }

    /**
     * Method to get timestamp
     *
     * @return raw timestamp
     */
    public static long getTimeStampRaw() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * @param ts1 first
     * @param ts2 second
     * @return
     */
    public static int getHourDiffFromTimeStamp(long ts1, long ts2) {
        Log.d(TAG, "getHourDiffFromTimeStamp() called with: ts1 = [" + ts1 + "], ts2 = [" + ts2 + "]");
        long periodSeconds = (ts1 - ts2);
        Log.d(TAG, "getHourDiffFromTimeStamp: periodSeconds: " + periodSeconds);
        long hour = periodSeconds / (60 * 60);
        Log.d(TAG, "getHourDiffFromTimeStamp: hour: " + hour);
        return (int) hour;
    }

    public static String hourToDayHour(int hour) {
        return hour / 24 + " days " + hour % 24 + " hour";
    }

}
