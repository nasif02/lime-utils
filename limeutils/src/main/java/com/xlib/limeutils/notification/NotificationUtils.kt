package com.xlib.limeutils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build

import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder

import com.xlib.limeutils.R


/**
 * Created by Xplo on 5/8/2016.
 */
object NotificationUtils {

    private val CHANNEL_ID = "notify_001"

    fun showNotification(context: Context, notBundle: NotBundle, pendingIntent: PendingIntent?) {

        if (notBundle.title == null) return
        if (notBundle.body == null) return

        val mBuilder = NotificationCompat.Builder(context.applicationContext, CHANNEL_ID)

        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle(notBundle.title)
        bigTextStyle.bigText(notBundle.body)
        //bigText.setSummaryText("Text in detail");

        if (pendingIntent != null) {
            mBuilder.setContentIntent(pendingIntent)
        }
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        mBuilder.setContentTitle(notBundle.title)
        mBuilder.setContentText(notBundle.body)
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(bigTextStyle)
        mBuilder.setAutoCancel(true)

        createNotification(context, mBuilder)


    }


    fun showNotification(context: Context, notBundle: NotBundle, pendingIntent: PendingIntent?, bitmap: Bitmap) {

        if (notBundle.title == null) return
        if (notBundle.body == null) return

        val mBuilder = NotificationCompat.Builder(context.applicationContext, CHANNEL_ID)


        val notiStyle = NotificationCompat.BigPictureStyle()
        notiStyle.setBigContentTitle(notBundle.title)
        notiStyle.setSummaryText(notBundle.body)
        notiStyle.bigPicture(bitmap)

        if (pendingIntent != null) {
            mBuilder.setContentIntent(pendingIntent)
        }
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        mBuilder.setContentTitle(notBundle.title)
        mBuilder.setContentText(notBundle.body)
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(notiStyle)
        mBuilder.setAutoCancel(true)


        createNotification(context, mBuilder)

    }


    private fun createNotification(context: Context, mBuilder: NotificationCompat.Builder) {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    CHANNEL_ID, "Bongo Channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, mBuilder.build())
    }


}
