package com.xlib.limeutils.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

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
        ab.setMessage(AppInfo.getAboutInfo());
        ab.setCancelable(true);
        ab.setNeutralButton("Ok",null);
        ab.show();

    }

    public void shareApp() {

        String textToShareAppPromote = AppInfo.getTextToShareApp();
        clipData = ClipData.newPlainText("text", textToShareAppPromote);
        clipboardManager.setPrimaryClip(clipData);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShareAppPromote);
        context.startActivity(Intent.createChooser(intent,
                "Share " + AppInfo.APP_TITLE));

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

        try {
            clipData = ClipData.newPlainText("text", text);
            clipboardManager.setPrimaryClip(clipData);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(intent, "Share By"));
        } catch (Exception e) {
        }

    }


    public void moreApps() {
        try {

            Uri uri = Uri.parse("market://search?q=pub:" + AppInfo.DEVELOPER_CODE);
            Intent moreApps = new Intent(Intent.ACTION_VIEW, uri);
            // intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(moreApps);

        } catch (android.content.ActivityNotFoundException anfe) {

            Uri uri = Uri
                    .parse("http://play.google.com/store/apps/developer?id="
                            + AppInfo.DEVELOPER_CODE);
            Intent moreApps = new Intent(Intent.ACTION_VIEW, uri);
            // intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(moreApps);

        }
    }

    public void openAWebsite(String url) {
        try {

            Uri uri = Uri
                    .parse(url);
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
                    new String[]{"nasif.002@gmail.com"});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback " + AppInfo.APP_TITLE);
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n..." + "");
            context.startActivity(Intent.createChooser(Email, "Send Feedback"));

        } catch (Exception e) {
        }
    }

    public void rate() {
        try {

            AppRaterUtils.showRateDialog(context);
            return;

        } catch (Exception e) {
        }

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + AppInfo.APP_PACKAGE));
        context.startActivity(intent);

    }

    public void marketLink(String appPackageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName));
            context.startActivity(intent);

        } catch (android.content.ActivityNotFoundException anfe) {


            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            context.startActivity(intent);

        }
    }



}
