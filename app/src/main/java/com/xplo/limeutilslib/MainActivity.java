package com.xplo.limeutilslib;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xlib.limeutils.core.Contextor;
import com.xlib.limeutils.database.DbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contextor.getInstance().init(getApplicationContext());

//        new AppRater.Builder()
//                .setDayThreshold(4)
//                .setCounterThreshold(3)
//                .build()
//                .appLaunched();


    }

}
