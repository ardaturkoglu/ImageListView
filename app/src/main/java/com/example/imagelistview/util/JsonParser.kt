package com.example.imagelistview.util

import android.content.Context
import com.example.imagelistview.model.ImageDataModel
import com.example.imagelistview.model.ImageList
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class JsonParser {
    private var jsonString : String? = null
    fun getJsonDataFromAsset(context: Context, fileName: String) : ImageList {
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            this.jsonString = null
        }
        return parseImageList()
    }

    private fun parseImageList() : ImageList{
           return Gson().fromJson(jsonString, ImageList::class.java)
    }
}