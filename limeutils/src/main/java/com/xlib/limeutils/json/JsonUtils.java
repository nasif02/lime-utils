package com.xlib.limeutils.json;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Copyright 2018 (C) Xplo
 * <p>
 * Created  : 11/6/2018
 * Author   : Xplo
 * Version  : 1.0
 * Desc     : mine.dpe.formmodel.dummydata
 * Comment  :
 */
public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static String toJsonPretty(Object o) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(o);
        return json;

    }

    public static String toJsonLowerCaseWithUnderscore(Object o) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(o);
        return json;

    }


    public static String toJson(Object o) {
        Gson gson = new Gson();
        String json = gson.toJson(o);
        return json;

    }

    public static Object fromJson(String json, Class ourClass) {
        Gson gson = new Gson();
        Object o = gson.fromJson(json, ourClass);

        return o;

    }

    /**
     * Build an object from the specified JSON resource using Gson.
     *
     * @param type The type of the object to build.
     * @return An object of type T, with member fields populated using Gson.
     */
    public <T> T mapToClass(String json, Class<T> type) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, type);
    }


    public JSONObject getAsJSONObject(String json) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONArray getAsJSONArray(String json) {

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


}
