package com.example.imagelistview.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("imageId")
    val imageId : Int?,
    @SerializedName("imageUrl")
    val imageUrl : String?
)

data class ImageList(
    @SerializedName("imageList")
    val imageList: List<ImageDataModel>?
)
