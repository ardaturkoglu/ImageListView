package com.example.imagelistview.view.presentation

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.imagelistview.view.data.ImageListRepositoryImpl
import com.example.imagelistview.view.model.ImageList
import com.example.imagelistview.view.network.RetrofitClient
import com.example.imagelistview.view.util.getJsonDataFromAsset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ImageListView(context: Context, attributeSet: AttributeSet) :
    RecyclerView(context, attributeSet) {

    private lateinit var imageList: ImageList
    private var service = RetrofitClient().getMyApi()
    private var imageListRepository: ImageListRepositoryImpl = ImageListRepositoryImpl(service)

    init {
        //Add RecyclerView Decoration
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    fun setImageListFromJson(fileName: String) {
        imageList = getJsonDataFromAsset(context, fileName)
        adapter = ImageListAdapter(imageList)
    }

    private fun getLoadingTimes() = (adapter as ImageListAdapter).loadingTimes

    fun submitImageListLoadingTimes() {
        //Send request
        CoroutineScope(Dispatchers.Main).launch {
            showToast(imageListRepository.postLoadingTimes(getLoadingTimes()))
        }
    }

    private fun showToast(isSuccessful: Boolean) {
        if (isSuccessful)
            Toast.makeText(context, "Request successful!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Request unsuccessful :(", Toast.LENGTH_SHORT).show()
    }
}