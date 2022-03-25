package com.example.imagelistview.view.data

import com.example.imagelistview.view.network.ApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageListRepositoryImpl(private val apiService : ApiModel):ImageListRepository {
    override suspend fun postLoadingTimes(loadingTimes: List<Long>?) : Boolean  =
        withContext(Dispatchers.IO){
           return@withContext apiService.postLoadingTimes(loadingTimes).execute().isSuccessful
        }

}