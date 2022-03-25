package com.example.imagelistview.view.util

import android.content.Context
import com.example.imagelistview.view.model.ImageList
import com.google.gson.Gson
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): ImageList {
     val jsonString: String?  = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }
    return parseImageList(jsonString)
}

private fun parseImageList(jsonString:String?): ImageList = Gson().fromJson(jsonString, ImageList::class.java)


