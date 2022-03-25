package com.example.imagelistview.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagelistview.data.ImageListRepository
import com.example.imagelistview.data.ImageListRepositoryImpl
import com.example.imagelistview.model.ImageList
import com.example.imagelistview.network.RetrofitClient
import com.example.imagelistview.util.getJsonDataFromAsset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    lateinit var imageList: ImageList
    private var service = RetrofitClient().getMyApi()
    private var imageListRepository: ImageListRepositoryImpl = ImageListRepositoryImpl(service)

    var submitImageListLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getImageList(context: Context) {
        imageList = getJsonDataFromAsset(context, "image-list.json")
    }

    fun submitImageList(loadingTimes: List<Long>?) =
        viewModelScope.launch {
            submitImageListLiveData.value = imageListRepository.postLoadingTimes(loadingTimes)
        }

}