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
import android.util.Log;

import com.xlib.limeutils.R;
import com.xlib.limeutils.base.Contextor;




public class AppInfo {

    private static Context context = Contextor.getInstance().getContext();

    //public static final int SPLASH_SCREEN_TIMEOUT = 4500;
//    public static final int SPLASH_SCREEN_TIMEOUT = 1000;

    private static final String TAG = AppInfo.class.getSimpleName();

    //app
    public static final String APP_PACKAGE = context.getPackageName();
    public static final String APP_NAME = context.getResources().getString(R.string.app_name);
    public static final String APP_TITLE = context.getResources().getString(R.string.app_title);
    public static final String APP_LINK = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
    public static final String APP_LINK_SHORT = context.getResources().getString(R.string.app_short_link);
    public static final String PROMO_TEXT = context.getResources().getString(R.string.app_promo_text);

    //DEVELOPER
    public static final String DEVELOPER_CODE = context.getResources().getString(R.string.app_developer_code);
    public static final String DEVELOPER_NAME = context.getResources().getString(R.string.app_developer_name);
    public static final String DEVELOPER_EMAIL = context.getResources().getString(R.string.app_developer_email);



//    //admob  this should be here in this class
//    public static final String MONE_UNIT_INTER_STARTUP = context.getResources().getString(R.string.mone_unit_inter_startup);
//    public static final String MONE_UNIT_BANNER_TS = context.getResources().getString(R.string.mone_unit_banner_ts);
//    public static final String MONE_UNIT_BANNER_LIST = context.getResources().getString(R.string.mone_unit_banner_list);


    /**
     * Method to get app version code
     * @return  app version code like 9
     */
    public static int getAppVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        if(packageInfo==null) return 0;
        return packageInfo.versionCode;

    }

    /**
     * Method to get app version name
     * @return  app version name like 2.3.0
     */
    public static String getAppVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        if(packageInfo==null) return "";
        return packageInfo.versionName;
    }

    /**
     * method to get package inof
     * @return
     */
    private static PackageInfo getPackageInfo(){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get about info
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
                + "\nContact : " + DEVELOPER_EMAIL + "\n" + ABOUT_TEXT_EXTRA
                + "";

        return s;
    }

    public static String getTextToShareApp() {

        String s = APP_TITLE + "\n" + PROMO_TEXT + "\n" + APP_LINK;
        return s;
    }

    public static boolean isDbgModeEnabled() {

        String s = context.getResources().getString(R.string.dbg_mode);
        return s.equals("1");

    }

    public static boolean isAdEnabled() {

        String s = context.getResources().getString(R.string.monetization);

        //return (s.equals("true"))? true:false;
        return s.equals("1");

    }

 public static boolean isPersonalAdCampEnabled() {

        String s = context.getResources().getString(R.string.personal_ad_camp);

        //return (s.equals("true"))? true:false;
        return s.equals("1");

    }


    /**
     * Method to check is this version is pro
     * @return  true if pro else false
     */
    public static boolean isPro() {

        //from shared preference
        //SwitchPreference pfPro;
        //pfPro = context.findPreference("pfPro");

        //return PrefUtils.getBoolean(Pk.pfIsPro, false);
        return true;

    }


}
