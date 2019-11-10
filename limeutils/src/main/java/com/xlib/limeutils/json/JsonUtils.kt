package com.xlib.limeutils.json


import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * Copyright 2018 (C) Xplo
 *
 *
 * Created  : 11/6/2018
 * Author   : Xplo
 * Version  : 1.0
 * Desc     : mine.dpe.formmodel.dummydata
 * Comment  :
 */
class JsonUtils {

    companion object {

        private val TAG = "JsonUtils"

        fun toJsonPretty(o: Any): String {
            val gson = GsonBuilder()
                    .setPrettyPrinting()
                    .create()
            return gson.toJson(o)

        }

        fun toJsonLowerCaseWithUnderscore(o: Any): String {
            val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create()
            return gson.toJson(o)

        }


        fun toJson(o: Any): String {
            val gson = Gson()
            return gson.toJson(o)

        }

        fun fromJson(json: String, ourClass: Class<*>): Any {
            val gson = Gson()

            return gson.fromJson(json, ourClass)

        }

        /**
         * Build an object from the specified JSON resource using Gson.
         *
         * @param type The type of the object to build.
         * @return An object of type T, with member fields populated using Gson.
         */
        fun <T> mapToClass(json: String, type: Class<T>): T {
            val gson = GsonBuilder().create()
            return gson.fromJson(json, type)
        }


        fun getAsJSONObject(json: String): JSONObject? {

            var jsonObject: JSONObject? = null
            try {
                jsonObject = JSONObject(json)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return jsonObject
        }

        fun getAsJSONArray(json: String): JSONArray? {

            var jsonArray: JSONArray? = null
            try {
                jsonArray = JSONArray(json)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return jsonArray
        }
    }


}
