package com.example.imagelistview.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.example.imagelistview.model.ImageList

class ImageListView(context:Context,attributeSet: AttributeSet): RecyclerView(context,attributeSet) {

    fun setImageListFromJson(imageList: ImageList?){
        adapter = ImageListAdapter(imageList)
    }

    fun getLoadingTimes() = (adapter as ImageListAdapter).loadingTimes
}