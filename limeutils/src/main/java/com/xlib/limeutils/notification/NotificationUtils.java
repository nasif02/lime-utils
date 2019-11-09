package com.xlib.limeutils.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.xlib.limeutils.R;


/**
 * Created by Xplo on 5/8/2016.
 */
public class NotificationUtils {

    private static final String CHANNEL_ID = "notify_001";

    public static void showNotification(Context context, NotBundle notBundle, PendingIntent pendingIntent) {

        if (notBundle == null) return;
        if (notBundle.getTitle() == null) return;
        if (notBundle.getBody() == null) return;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(notBundle.getTitle());
        bigTextStyle.bigText(notBundle.getBody());
        //bigText.setSummaryText("Text in detail");

        if (pendingIntent != null) {
            mBuilder.setContentIntent(pendingIntent);
        }
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle(notBundle.getTitle());
        mBuilder.setContentText(notBundle.getBody());
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigTextStyle);
        mBuilder.setAutoCancel(true);

        createNotification(context,mBuilder);



    }



    public static void showNotification(Context context, NotBundle notBundle, PendingIntent pendingIntent, Bitmap bitmap) {

        if (notBundle == null) return;
        if (notBundle.getTitle() == null) return;
        if (notBundle.getBody() == null) return;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);


        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setBigContentTitle(notBundle.getTitle());
        notiStyle.setSummaryText(notBundle.getBody());
        notiStyle.bigPicture(bitmap);

        if (pendingIntent != null) {
            mBuilder.setContentIntent(pendingIntent);
        }
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle(notBundle.getTitle());
        mBuilder.setContentText(notBundle.getBody());
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(notiStyle);
        mBuilder.setAutoCancel(true);


        createNotification(context,mBuilder);

    }


    private static void createNotification(Context context, NotificationCompat.Builder mBuilder) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Bongo Channel", NotificationManager.IMPORTANCE_DEFAULT);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(0, mBuilder.build());
        }
    }


}
