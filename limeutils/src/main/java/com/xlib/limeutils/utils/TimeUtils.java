package com.xlib.limeutils.utils;

import android.util.Log;

import java.util.Date;

/**
 * Copyright 2019 (C) xplo
 * <p>
 * Created  : 2019-11-08
 * Updated  :
 * Author   : xplo
 * Desc     : com.xlib.limeutils.utils
 * Comment  :
 */
public class TimeUtils {

    public static int getDayDifference(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null)
            return 0;

        int day = (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        return day;
    }
}
