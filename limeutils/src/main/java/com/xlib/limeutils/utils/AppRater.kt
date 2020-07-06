/*
 * Created by Xplo on 1/4/2016.
 *
 * Need This Context to show dialog
 */

package com.xlib.limeutils.utils


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AlertDialog

class AppRater(builder: Builder) {

    private val dayThreshold: Int
    private val counterThreshold: Int
    private val posButtonText: String
    private val negButtonText: String
    private val title: String
    private val message: String
    private val isNetworkCheck: Boolean


    init {
        this.dayThreshold = builder.dayThreshold
        this.counterThreshold = builder.counterThreshold
        this.posButtonText = builder.posButtonText
        this.negButtonText = builder.negButtonText
        this.title = builder.title
        this.message = builder.message
        this.isNetworkCheck = builder.isNetworkCheck
    }

    fun appLaunched(context: Context) {

        if (PrefUtils.getInstance(context).getBoolean(AR_DONT_SHOW_AGAIN, false)) {
            return
        }

        // Increment launch counter
        val launchCounter = PrefUtils.getInstance(context).getLong(AR_LAUNCH_COUNTER, 0) + 1
        PrefUtils.getInstance(context).putLong(AR_LAUNCH_COUNTER, launchCounter)

        // Get date of first launch
        var dateFirstLaunch = PrefUtils.getInstance(context).getLong(AR_DATE_FIRST_LAUNCH, 0)
        if (dateFirstLaunch == 0L) {
            dateFirstLaunch = System.currentTimeMillis()
            PrefUtils.getInstance(context).putLong(AR_DATE_FIRST_LAUNCH, dateFirstLaunch)
        }

        // Wait at least n days and launch threshold before opening
        if (launchCounter < counterThreshold) return
        if (!isPassDayThreshold(dateFirstLaunch)) return

        if (isNetworkCheck) {
            if (!isNetworkConnected(context)) return
        }

        showRateDialog(context)

    }

    //need this context
    fun showRateDialog(context: Context) {

        val appPackage = context.packageName

        val builder = AlertDialog.Builder(context)

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)

        builder.setPositiveButton(posButtonText) { dialog, which ->
            dontShowAgain(context)

            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=$appPackage")))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton(negButtonText) { dialog, which ->
            dialog.dismiss()
            resetCounter(context)
        }

        val mAlertDialog = builder.create()
        mAlertDialog.show()

    }

    private fun resetCounter(context: Context) {
        PrefUtils.getInstance(context).putLong(AR_LAUNCH_COUNTER, 0)
    }


    private fun dontShowAgain(context: Context) {
        PrefUtils.getInstance(context).putBoolean(AR_DONT_SHOW_AGAIN, true)
    }

    private fun isPassDayThreshold(dateFirstLaunch: Long): Boolean {
        return System.currentTimeMillis() >= dateFirstLaunch + dayThreshold * 24 * 60 * 60 * 1000

    }


    class Builder {

        var dayThreshold = 3
        var counterThreshold = 7
        var posButtonText = "Rate"
        var negButtonText = "Later"
        var title = "Rate"
        var message = "If you enjoy " + AppInfo.APP_TITLE + ", please take a moment to rate it. Thanks for your support."
        var isNetworkCheck = true

        fun setDayThreshold(dayThreshold: Int): Builder {
            this.dayThreshold = dayThreshold
            return this
        }

        fun setCounterThreshold(counterThreshold: Int): Builder {
            this.counterThreshold = counterThreshold
            return this
        }

        fun setPosButtonText(posButtonText: String): Builder {
            this.posButtonText = posButtonText
            return this
        }

        fun setNegButtonText(negButtonText: String): Builder {
            this.negButtonText = negButtonText
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setNetworkCheck(isNetworkCheck: Boolean): Builder {
            this.isNetworkCheck = isNetworkCheck
            return this
        }

        fun build(): AppRater {
            return AppRater(this)
        }


    }

    companion object {

        private const val TAG = "AppRater"


        private const val AR_LAUNCH_COUNTER = "AR_LAUNCH_COUNTER"
        private const val AR_DONT_SHOW_AGAIN = "AR_DONT_SHOW_AGAIN"
        private const val AR_DATE_FIRST_LAUNCH = "AR_DATE_FIRST_LAUNCH"

        /**
         * Method to check internet is avalable
         * need permission in manifest
         *
         * @param context
         * @return
         */
        fun isNetworkConnected(context: Context): Boolean {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                @SuppressLint("MissingPermission") val info = connectivity.allNetworkInfo
                if (info != null)
                    for (i in info.indices)
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }

            }
            return false
        }
    }


}