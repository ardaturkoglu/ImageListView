package com.example.imagelistview

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.imagelistview.model.ImageDataModel

class ImageListView(context:Context,attributeSet: AttributeSet): ListView(context,attributeSet) {

    private var imageList:List<ImageDataModel>? = null

    fun setImageListFromJson(imageList: List<ImageDataModel>?){
        this.imageList = imageList
        adapter = ArrayAdapter(context,R.layout.item_simple_image, mutableListOf(imageList))
    }


}