package com.xplo.limeutilslib;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.xlib.limeutils.base.Contextor;
import com.xlib.limeutils.database.DbHelper;
import com.xlib.limeutils.utils.PrefUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contextor.getInstance().init(getApplicationContext());

        boolean dbExist = DbHelper.getInstance("db.db").checkDataBase();
        Log.d(TAG, "onCreate: " + dbExist);



        try {
            DbHelper.getInstance().createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbExist = DbHelper.getInstance("db.db").checkDataBase();
        Log.d(TAG, "onCreate: " + dbExist);


        getListItems();



    }

    private void getListItems() {

        List<String> listArray = new ArrayList<>();

        String sql = null;
        Cursor cursor;

        sql = "select * from ";

    }
}
