package com.example.imagelistview.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.imagelistview.model.ImageList
import com.example.imagelistview.util.getJsonDataFromAsset

class MainViewModel: ViewModel() {
    private lateinit var imageList : ImageList

    private fun getImageList(context: Context){
        imageList = getJsonDataFromAsset(context, "image-list.json")
    }
}