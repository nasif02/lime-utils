package com.xlib.limeutils.json;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonLoader {

    private static final String TAG = "JsonLoader";

    private Context context;
    //private String fileName;

//    private static final int DIR_ASSET = 1;
//    private static final int DIR_RAW = 2;
//    private static final int DIR_EXTERNAL_STORAGE = 3;

    private JsonLoader(Context context) {
        this.context = context;
    }

    public static JsonLoader with(Context applicationContext) {
        return new JsonLoader(applicationContext);
    }

//    public JsonLoader fileName(String fileName) {
//        this.fileName = fileName;
//        return this;
//    }

    public String getAsString(String fileName) {
        String json = null;
        try {
            InputStream inputStream = getInputStream(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getAsString(int res) {
        String json = null;
        try {
            InputStream inputStream = getInputStream(res);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    public JSONObject getAsJSONObject(String fileName) {

        String json = getAsString(fileName);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONArray getAsJSONArray(String fileName) {
        String json = getAsString(fileName);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    private InputStream getInputStream(String fileName) throws IOException {

        InputStream inputStream = context.getAssets().open(fileName);
        return inputStream;
    }

    private InputStream getInputStream(int res) throws IOException {

        InputStream inputStream = context.getResources().openRawResource(res);
        return inputStream;
    }



}
