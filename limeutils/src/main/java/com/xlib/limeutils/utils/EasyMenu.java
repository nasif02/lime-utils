package com.xlib.limeutils.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;

public class EasyMenu {

    private Context context;
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    /**
     *
     * @param context pass this, its needed
     */
    public EasyMenu(Context context) {
        super();
        this.context = context;
        clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void showAboutText() {

        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("about");
        ab.setMessage(AppInfo.INSTANCE.getAboutInfo());
        ab.setCancelable(true);
        ab.setNeutralButton("Ok",null);
        ab.show();

    }

    public void shareApp() {

        String textToShareAppPromote = AppInfo.INSTANCE.getTextToShareApp();
        clipData = ClipData.newPlainText("text", textToShareAppPromote);
        clipboardManager.setPrimaryClip(clipData);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShareAppPromote);
        context.startActivity(Intent.createChooser(intent,
                "Share " + AppInfo.INSTANCE.getAPP_TITLE()));

    }

    public void shareText(String text) {

        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(intent, "Share By"));
        } catch (Exception e) {
        }

    }

    public void addToClip(String text) {

        clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);

    }

    public void shareTextWithClip(String text) {

        addToClip(text);
        shareText(text);

    }


    public void moreApps() {
        try {
            Intent moreApps = new Intent(Intent.ACTION_VIEW, AppInfo.INSTANCE.getDeveloperUri());
            // intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(moreApps);

        } catch (android.content.ActivityNotFoundException e) {

            Intent moreApps = new Intent(Intent.ACTION_VIEW, AppInfo.INSTANCE.getDeveloperUriWeb());
            // intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(moreApps);

        }
    }

    public void openAWebsite(String url) {
        try {

            Uri uri = Uri.parse(url);
            Intent moreApps = new Intent(Intent.ACTION_VIEW, uri);
            // intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(moreApps);

        } catch (Exception e) {

        }
    }


    public void feedback() {
        try {
            Intent Email = new Intent(Intent.ACTION_SEND);
            // Email.setType("text/email");
            Email.setType("plain/text");
            // Email.setType("message/rfc822");

            Email.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{AppInfo.INSTANCE.getDEVELOPER_EMAIL()});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback " + AppInfo.INSTANCE.getAPP_TITLE());
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n..." + "");
            context.startActivity(Intent.createChooser(Email, "Send Feedback"));

        } catch (Exception e) {
        }
    }

    public void rate() {
        try {

            new AppRater.Builder(context)
                    .build()
                    .showRateDialog();
            return;

        } catch (Exception e) {
        }

    }

    public void goToPlayStore() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, AppInfo.INSTANCE.getAppUri());
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, AppInfo.INSTANCE.getAppUriWeb());
            context.startActivity(intent);

        }
    }







}
