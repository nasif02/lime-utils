package com.xplo.limeutilslib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.xlib.limeutils.base.Contextor;
import com.xlib.limeutils.database.DbHelper;
import com.xlib.limeutils.utils.AppRater;
import com.xlib.limeutils.utils.PrefUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contextor.getInstance().init(getApplicationContext());

        new AppRater.Builder(this)
                .setDayThreshold(0)
                .setCounterThreshold(3)
                .build()
                .appLaunched();


    }

}
