/*
 * Created by Xplo on 1/4/2016.
 *
 *
 */

package com.xlib.limeutils.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;

import com.xlib.limeutils.R;


/**
 * Need This Context to show dialog
 */
public class AppRater {

    private static final String TAG = "AppRater";

    private Context context;
    private int dayThreshold;
    private int counterThreshold;
    private String posButtonText;
    private String negButtonText;
    private String title;
    private String message;


    private static final String AR_LAUNCH_COUNTER = "AR_LAUNCH_COUNTER";
    private static final String AR_DONT_SHOW_AGAIN = "AR_DONT_SHOW_AGAIN";
    private static final String AR_DATE_FIRST_LAUNCH = "AR_DATE_FIRST_LAUNCH";


    public AppRater(Builder builder) {
        this.context = builder.context;
        this.dayThreshold = builder.dayThreshold;
        this.counterThreshold = builder.counterThreshold;
        this.posButtonText = builder.posButtonText;
        this.negButtonText = builder.negButtonText;
        this.title = builder.title;
        this.message = builder.message;
    }

    public void appLaunched() {

        if (PrefUtils.getInstance().getBoolean(AR_DONT_SHOW_AGAIN, false)) {
            return;
        }

        // Increment launch counter
        long launchCounter = PrefUtils.getInstance().getLong(AR_LAUNCH_COUNTER, 0) + 1;
        PrefUtils.getInstance().putLong(AR_LAUNCH_COUNTER, launchCounter);

        // Get date of first launch
        long dateFirstLaunch = PrefUtils.getInstance().getLong(AR_DATE_FIRST_LAUNCH, 0);
        if (dateFirstLaunch == 0) {
            dateFirstLaunch = System.currentTimeMillis();
            PrefUtils.getInstance().putLong(AR_DATE_FIRST_LAUNCH, dateFirstLaunch);
        }

        // Wait at least n days and launch threshold before opening
        if (launchCounter < counterThreshold) return;
        if (!isPassDayThreshold(dateFirstLaunch)) return;

//        if (!isConnectedToInternet(context)) return;

        showRateDialog();

    }

    //need this context
    public void showRateDialog() {

        final String appPackage = context.getPackageName();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton(posButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dontShowAgain();

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=" + appPackage)));

                dialog.dismiss();
            }

        });

        builder.setNegativeButton(negButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                resetCounter();
            }
        });

        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();

    }

    private void resetCounter() {
        PrefUtils.getInstance().putLong(AR_LAUNCH_COUNTER, 0);
    }


    private void dontShowAgain() {
        PrefUtils.getInstance().putBoolean(AR_DONT_SHOW_AGAIN, true);
    }

    private boolean isPassDayThreshold(long dateFirstLaunch) {
        if (System.currentTimeMillis() >= dateFirstLaunch
                + (dayThreshold * 24 * 60 * 60 * 1000)) {
            return true;
        }
        return false;

    }

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


    public static class Builder {

        private Context context;
        private int dayThreshold = 3;
        private int counterThreshold = 7;
        private String posButtonText = "Rate";
        private String negButtonText = "Later";
        private String title = "Rate";
        private String message = "If you enjoy " + AppInfo.APP_TITLE
                + ", please take a moment to rate it. Thanks for your support.";

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDayThreshold(int dayThreshold) {
            this.dayThreshold = dayThreshold;
            return this;
        }

        public Builder setCounterThreshold(int counterThreshold) {
            this.counterThreshold = counterThreshold;
            return this;
        }

        public Builder setPosButtonText(String posButtonText) {
            this.posButtonText = posButtonText;
            return this;
        }

        public Builder setNegButtonText(String negButtonText) {
            this.negButtonText = negButtonText;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public AppRater build() {
            return new AppRater(this);
        }


    }


}