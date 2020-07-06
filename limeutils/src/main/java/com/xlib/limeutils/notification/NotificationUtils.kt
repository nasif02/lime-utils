package com.xlib.limeutils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xlib.limeutils.R


/**
 * Created by Xplo on 5/8/2016.
 */
object NotificationUtils {

    const val CHANNEL_ID = "CHANNEL_XPLO"
    const val CHANNEL_NAME = "CHANNEL_NAME"
    const val CHANNEL_DESC = "CHANNEL_DESC"

    /**
     * create default notification channel
     */
    fun createNotificationChannel(context: Context) {
        createNotificationChannel(context, CHANNEL_ID, CHANNEL_NAME, CHANNEL_DESC)
    }

    /**
     * create customize notification channel
     */
    fun createNotificationChannel(
            context: Context,
            channelId: String,
            name: String,
            desc: String?) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT)
        if (desc != null) {
            channel.description = desc
        }
        notificationManager.createNotificationChannel(channel)

    }


    /**
     * show a simple notification
     */
    fun showNotification(context: Context, title: String, body: String?, icon: Int,
                         pendingIntent: PendingIntent?, channelId: String?, notificationId: Int) {

        val notificationManager = NotificationManagerCompat.from(context)
        val notification = createNotification(context, title, body, icon, pendingIntent, channelId)
        notificationManager.notify(notificationId, notification)

    }

    /**
     * show a image notification
     */
    fun showImageNotification(context: Context, title: String, body: String?, icon: Int,
                              pendingIntent: PendingIntent?, channelId: String?, bitmap: Bitmap, notificationId: Int) {

        val notificationManager = NotificationManagerCompat.from(context)
        val notification = createImageNotification(context, title, body, icon, pendingIntent, channelId, bitmap)
        notificationManager.notify(notificationId, notification)

    }


    /**
     * create simple notification
     */
    fun createNotification(context: Context, title: String, body: String?, icon: Int,
                           pendingIntent: PendingIntent?, channelId: String?): Notification {

        val builder: NotificationCompat.Builder

        if (channelId != null) {
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context, CHANNEL_ID)
        }


        builder.setContentTitle(title)
        builder.setContentText(body)
        builder.setSmallIcon(icon)

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
        }

        return builder.build()
    }

    /**
     * create image notification
     */
    fun createImageNotification(context: Context, title: String, body: String?, icon: Int,
                                pendingIntent: PendingIntent?, channelId: String?, bitmap: Bitmap): Notification {

        val builder: NotificationCompat.Builder

        if (channelId != null) {
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context, CHANNEL_ID)
        }


        builder.setContentTitle(title)
        builder.setContentText(body)
        builder.setSmallIcon(icon)
        //builder.setAutoCancel(true)

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
        }

        val style = NotificationCompat.BigPictureStyle()
        style.setBigContentTitle(title)
        style.setSummaryText(body)
        style.bigPicture(bitmap)

        builder.setStyle(style)

        return builder.build()
    }

    /**
     * create a simple expand notification
     * not fully completed
     */
    fun createExpandNotification(context: Context, bundle: ExpandNotBundle, icon: Int,
                                 pendingIntent: PendingIntent?, channelId: String?): Notification {

        val collapsedViews = RemoteViews(context.packageName, bundle.layoutCollapsed)
        val expandedViews = RemoteViews(context.packageName, bundle.layoutExpanded)


//        val intent = Intent(context, NotificationReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context,0, intent, 0)


        collapsedViews.setTextViewText(R.id.tvTitle, bundle.title)
        collapsedViews.setTextViewText(R.id.tvBody, bundle.body)

        expandedViews.setTextViewText(R.id.tvTitle, bundle.titleExpanded)
        expandedViews.setTextViewText(R.id.tvBody, bundle.bodyExpanded)
        expandedViews.setOnClickPendingIntent(R.id.root, pendingIntent)

        val builder: NotificationCompat.Builder

        if (channelId != null) {
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context, CHANNEL_ID)
        }

        builder.setSmallIcon(icon)
        builder.setCustomContentView(collapsedViews)
        builder.setCustomBigContentView(expandedViews)
        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())

        return builder.build()
    }

    /**
     * show expand notification
     */
    fun showExpandNotification(context: Context, bundle: ExpandNotBundle, icon: Int,
                               pendingIntent: PendingIntent?, channelId: String?, notificationId: Int) {

        val notificationManager = NotificationManagerCompat.from(context)
        val notification = createExpandNotification(context, bundle, icon, pendingIntent, channelId)
        notificationManager.notify(notificationId, notification)

    }


}
