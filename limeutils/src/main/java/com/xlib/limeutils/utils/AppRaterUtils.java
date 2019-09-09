/*
 * Created by Xplo on 1/4/2016.
 *
 *
 */

package com.xlib.limeutils.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

import com.xlib.limeutils.base.Lc;
import com.xlib.limeutils.network.NetworkUtils;



public class AppRaterUtils {


    private static final String TAG = AppRaterUtils.class.getSimpleName() + Lc.TAG_POSTFIX;

    private static String appTitle = AppInfo.APP_TITLE;
    private static String appPackage = AppInfo.APP_PACKAGE;

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 7;

//    private final static int DAYS_UNTIL_PROMPT = 0;
//    private final static int LAUNCHES_UNTIL_PROMPT = 1;

    /**
     *
     * @param mContext Need This Context to show dialog
     */
    public static void appLaunched(Context mContext) {

        if (PrefUtils.getBoolean(Pk.DONT_SHOW_AGAIN, false)) {
            Log.d(TAG, "appLaunched: DONT_SHOW_AGAIN: true");
            return;
        }

        // Increment launch counter
        long tLaunch = PrefUtils.getLong(Pk.LAUNCH_COUNTER, 0) + 1;
        Log.d(TAG, "appLaunched: tLaunch: " + tLaunch);
        PrefUtils.putLong(Pk.LAUNCH_COUNTER, tLaunch);

        // Get date of first launch
        Long dateFirstLaunch = PrefUtils.getLong(Pk.DATE_FIRST_LAUNCH, 0);
        Log.d(TAG, "appLaunched: dateFirstLaunch: " + dateFirstLaunch);

        if (dateFirstLaunch == 0) {
            dateFirstLaunch = System.currentTimeMillis();
            PrefUtils.putLong(Pk.DATE_FIRST_LAUNCH, dateFirstLaunch);
        }

        // Wait at least n days before opening
        if (tLaunch >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= dateFirstLaunch
                    + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {

                //ConDetector cd = new ConDetector(mContext);
                if (NetworkUtils.isConnectingToInternet(mContext)) {
                    Log.d(TAG, "appLaunched: Internet Connected");
                    showRateDialog(mContext);
                } else {
                    Log.d(TAG, "appLaunched: Internet Not Connected");
                    //showRateDialog(mContext);
                }
            }
        }

    }

    //need this context
    public static void showRateDialog(final Context mContext) {

        String title = "Rate";
        String message = "If you enjoy " + appTitle
                + ", please take a moment to rate it. Thanks for your support.";

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("Rate", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                Log.d(TAG, "showRateDialog: Rate Button Clicked");


                PrefUtils.putBoolean(Pk.DONT_SHOW_AGAIN, true);

                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=" + appPackage)));

                dialog.dismiss();
            }

        });

        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                Log.d(TAG, "showRateDialog: Later Button Clicked");

                dialog.dismiss();
            }
        });

        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();

    }


}