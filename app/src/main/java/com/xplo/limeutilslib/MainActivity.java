package com.xplo.limeutilslib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contextor.getInstance().init(getApplicationContext());

//        boolean dbExist = DbHelper.getInstance("db.db").isDbExist();
//        Log.d(TAG, "onCreate: " + dbExist);
//

        db = new DbHelper(this,"db2.db");

//        try {
//            db.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        db.openDataBase();

        db.createTableDemo();

        getListItems();

        insertItem();

        getListItems();



    }

    private void insertItem() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "title");
        contentValues.put("body", "body");
        contentValues.put("bookmark", 1);
        contentValues.put("category", "aaa");

        db.insertRow("demo", contentValues);
    }

    private void getListItems() {

        List<String> listArray = new ArrayList<>();

        String sql = "select * from demo";

        Cursor cursor = db.runQueryRaw(sql,null);

        Log.d(TAG, "getListItems: " + cursor.getCount());


    }
}
