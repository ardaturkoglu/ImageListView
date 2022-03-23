package com.example.imagelistview

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ListView
import com.example.imagelistview.model.ImageDataModel
import retrofit2.http.Url

class ImageListView(context:Context,attributeSet: AttributeSet): ListView(context,attributeSet) {

    private val imageList:List<ImageDataModel>? = null

    private fun getImageListFromJson(jsonList: List<Url>){

    }
    private fun parseImageList(){

    }
}