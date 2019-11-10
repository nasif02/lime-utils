/*
 * Created by Xplo on 1/4/2016.
 *
 * dev name, email, app title, name, bangla title
 * app package name, link , shortlink
 * app promo text extra text
 *
 */

package com.xlib.limeutils.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri

import com.xlib.limeutils.R
import com.xlib.limeutils.core.Contextor
import com.xlib.limeutils.utils.AppInfo.APP_LINK
import com.xlib.limeutils.utils.AppInfo.APP_TITLE
import com.xlib.limeutils.utils.AppInfo.PROMO_TEXT


object AppInfo {

    private val TAG = "AppInfo"

    var resources = Contextor.getInstance().context.resources


    //app
    val APP_PACKAGE = Contextor.getInstance().context.packageName
    val APP_NAME = resources.getString(R.string.app_name)
    val APP_TITLE = resources.getString(R.string.app_title)
    val APP_LINK = "https://play.google.com/store/apps/details?id=$APP_PACKAGE"
    val APP_LINK_SHORT = resources.getString(R.string.app_short_link)
    val PROMO_TEXT = resources.getString(R.string.app_promo_text)

    //developer
    val DEVELOPER_CODE = resources.getString(R.string.app_developer_code)
    val DEVELOPER_NAME = resources.getString(R.string.app_developer_name)
    val DEVELOPER_EMAIL = resources.getString(R.string.app_developer_email)


    /**
     * Method to get app version code
     *
     * @return app version code like 9
     */
    val appVersionCode: Int
        get() {
            val packageInfo = packageInfo ?: return 0
            return packageInfo.versionCode

        }

    /**
     * Method to get app version name
     *
     * @return app version name like 2.3.0
     */
    val appVersionName: String
        get() {
            val packageInfo = packageInfo ?: return ""
            return packageInfo.versionName
        }

    /**
     * method to get package inof
     *
     * @return
     */
    private val packageInfo: PackageInfo?
        get() {
            val context = Contextor.getInstance().context
            try {
                return context.packageManager.getPackageInfo(context.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                return null
            }

        }

    /**
     * Method to get about info
     *
     * @return about info
     */
    val aboutInfo: String
        get() {

            var s: String? = null
            var pro = ""

            if (isPro()) {
                pro = " (Pro)"
            }

            s = (APP_TITLE + pro + "\nVersion : " + appVersionName
                    + "\nCopyright © 2016, Xplo" + "\nDeveloper : " + DEVELOPER_CODE
                    + "\nContact : " + DEVELOPER_EMAIL
                    + "")

            return s
        }

    val textToShareApp: String
        get() = APP_TITLE + "\n" + PROMO_TEXT + "\n" + APP_LINK


    val developerUri: Uri
        get() = Uri.parse("market://search?q=pub:$DEVELOPER_CODE")

    val developerUriWeb: Uri
        get() = Uri.parse("https://play.google.com/store/apps/developer?id=$DEVELOPER_CODE")


    val appUri: Uri
        get() = Uri.parse("market://details?id=$APP_PACKAGE")

    val appUriWeb: Uri
        get() = Uri.parse("https://play.google.com/store/apps/details?id=$APP_PACKAGE")


    /**
     * Method to check is this version is pro
     *
     * @return true if pro else false
     */
    fun isPro() : Boolean{
        //from shared preference
        //SwitchPreference pfPro;
        //pfPro = context.findPreference("pfPro");
        //return PrefUtils.getBoolean(Pk.pfIsPro, false);

        return true;
    }

}
