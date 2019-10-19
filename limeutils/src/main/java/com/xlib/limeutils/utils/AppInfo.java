/*
 * Created by Xplo on 1/4/2016.
 *
 * dev name, email, app title, name, bangla title
 * app package name, link , shortlink
 * app promo text extra text
 *
 */

package com.xlib.limeutils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.xlib.limeutils.R;
import com.xlib.limeutils.core.Contextor;


public class AppInfo {

    private static Context context = Contextor.getInstance().getContext();

    private static final String TAG = AppInfo.class.getSimpleName();

    //app
    public static final String APP_NAME = context.getResources().getString(R.string.app_name);
    public static final String APP_TITLE = context.getResources().getString(R.string.app_title);
    public static final String APP_LINK = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
    public static final String APP_LINK_SHORT = context.getResources().getString(R.string.app_short_link);
    public static final String PROMO_TEXT = context.getResources().getString(R.string.app_promo_text);

    //developer
    public static final String DEVELOPER_CODE = context.getResources().getString(R.string.app_developer_code);
    public static final String DEVELOPER_NAME = context.getResources().getString(R.string.app_developer_name);
    public static final String DEVELOPER_EMAIL = context.getResources().getString(R.string.app_developer_email);


    /**
     * Method to get app version code
     *
     * @return app version code like 9
     */
    public static int getAppVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) return 0;
        return packageInfo.versionCode;

    }

    /**
     * Method to get app version name
     *
     * @return app version name like 2.3.0
     */
    public static String getAppVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) return "";
        return packageInfo.versionName;
    }

    /**
     * method to get package inof
     *
     * @return
     */
    private static PackageInfo getPackageInfo() {
        Context context = Contextor.getInstance().getContext();
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get about info
     *
     * @return about info
     */
    public static String getAboutInfo() {

        String s = null;
        String pro = "";

        if (AppInfo.isPro()) {
            pro = " (Pro)";
        }

        s = APP_TITLE + pro + "\nVersion : " + getAppVersionName()
                + "\nCopyright © 2016, Xplo" + "\nDeveloper : " + DEVELOPER_CODE
                + "\nContact : " + DEVELOPER_EMAIL
                + "";

        return s;
    }

    public static String getTextToShareApp() {

        String s = APP_TITLE + "\n" + PROMO_TEXT + "\n" + APP_LINK;
        return s;
    }


    /**
     * Method to check is this version is pro
     *
     * @return true if pro else false
     */
    public static boolean isPro() {

        //from shared preference
        //SwitchPreference pfPro;
        //pfPro = context.findPreference("pfPro");

        //return PrefUtils.getBoolean(Pk.pfIsPro, false);
        return true;

    }

    public static Uri getDeveloperUri() {
        return Uri.parse("market://search?q=pub:" + DEVELOPER_CODE);
    }

    public static Uri getDeveloperUriWeb() {
        return Uri.parse("https://play.google.com/store/apps/developer?id=" + DEVELOPER_CODE);
    }


    public static Uri getAppUri() {
        return Uri.parse("market://details?id=" + context.getPackageName());
    }

    public static Uri getAppUriWeb() {
        return Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName());
    }


}
