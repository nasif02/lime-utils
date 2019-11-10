package com.xlib.limeutils.json

import android.content.Context

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class JsonLoader private constructor(private val context: Context) {

    companion object {

        private val TAG = "JsonLoader"

        fun with(applicationContext: Context): JsonLoader {
            return JsonLoader(applicationContext)
        }
    }


    fun getAsString(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream = getInputStream(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

    fun getAsString(res: Int): String? {
        var json: String? = null
        try {
            val inputStream = getInputStream(res)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }


    fun getAsJSONObject(fileName: String): JSONObject? {

        val json = getAsString(fileName)
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json!!)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonObject
    }

    fun getAsJSONArray(fileName: String): JSONArray? {
        val json = getAsString(fileName)
        var jsonArray: JSONArray? = null
        try {
            jsonArray = JSONArray(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonArray
    }

    @Throws(IOException::class)
    private fun getInputStream(fileName: String): InputStream {
        return context.assets.open(fileName)
    }

    @Throws(IOException::class)
    private fun getInputStream(res: Int): InputStream {
        return context.resources.openRawResource(res)
    }




}
