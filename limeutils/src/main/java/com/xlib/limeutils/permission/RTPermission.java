
/*
 * Created by Xplo on 11/22/2017.
 * <p>
 * Runtime Permission Handler
 */

package com.xlib.limeutils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;




public class RTPermission {

    private static final String TAG = RTPermission.class.getSimpleName();

    //starts with 1001
    public static final int PRC_RECORD_AUDIO = 1001;
    public static final int PRC_WRITE_EXTERNAL_STORAGE = 1002;
    public static final int PRC_CAMERA = 1003;
    public static final int PRC_CALL_PHONE = 1004;
    public static final int PRC_ACCESS_FINE_LOCATION = 1005;
    public static final int PRC_READ_CONTACTS = 1006;


    private Activity activity;


    //Try to make this class static. mContext not working here
    public RTPermission(Activity activity) {
        this.activity = activity;
    }


    //record audio
    public void requestPermissionForRecord() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            pToast("Microphone permission needed for recording. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, PRC_RECORD_AUDIO);

    }

    public boolean isPermissionForRecordGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    //ExternalStorage
    public void requestPermissionForExternalStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            pToast("External Storage permission needed. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PRC_WRITE_EXTERNAL_STORAGE);

    }

    public boolean isPermissionForExternalStorageGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    //camera
    public void requestPermissionForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            pToast("Camera permission needed. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PRC_CAMERA);

    }

    public boolean isPermissionForCameraGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    //call phone
    public void requestPermissionForCallPhone() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
            pToast("Phone permission needed. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, PRC_CALL_PHONE);

    }

    public boolean isPermissionForCallPhoneGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    //call phone
    public void requestPermissionForAccessFineLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            pToast("Location permission needed. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PRC_ACCESS_FINE_LOCATION);

    }

    public boolean isPermissionForAccessFineLocationGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    //read contacts
    public void requestPermissionForReadContatcs() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
            pToast("Location permission needed. Please allow in App Settings.");
        }
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, PRC_READ_CONTACTS);

    }

    public boolean isPermissionForReadContatcsGranted() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private void pToast(String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }


}
